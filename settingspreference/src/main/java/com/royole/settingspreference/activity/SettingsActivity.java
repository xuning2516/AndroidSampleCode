package com.royole.settingspreference.activity;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.royole.settingspreference.R;
import com.royole.settingspreference.fragment.CodePreferenceFragment;
import com.royole.settingspreference.fragment.CommentSettingsFragment;
import com.royole.settingspreference.fragment.GeneralSettingsFragment;
import com.royole.settingspreference.fragment.XmlPreferenceFragment;

public class SettingsActivity extends FragmentActivity implements XmlPreferenceFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        loadUI();
        prepareSettings();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void loadUI() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return;

        Window window = this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(0xffE46C62);
    }


    private void prepareSettings() {
        String title, fragId;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString("title");
            fragId = bundle.getString("frag_id");
        } else {
            title = getString(R.string.preference);
            fragId = "GeneralSettingsFragment";
        }

        TextView textView = (TextView) findViewById(R.id.settings_bar);
        textView.setText(title);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if ("GeneralSettingsFragment".equals(fragId)) {
            fragmentTransaction.replace(R.id.preferences_fragment, new GeneralSettingsFragment());
        } else if ("CommentSettingsFragment".equals(fragId)) {
            fragmentTransaction.replace(R.id.preferences_fragment, new CommentSettingsFragment());
        }else if ("XmlPreferenceFragment".equals(fragId)) {
            fragmentTransaction.replace(R.id.preferences_fragment, new XmlPreferenceFragment());
        }else if ("CodePreferenceFragment".equals(fragId)) {
            fragmentTransaction.replace(R.id.preferences_fragment, new CodePreferenceFragment());
        }
        fragmentTransaction.commit();
    }

    public void performBack(View view) {
        super.onBackPressed();
    }

    public void enterAccessibilityPage(View view) {
        Toast.makeText(this, getString(R.string.turn_on_toast), Toast.LENGTH_SHORT).show();
        Intent mAccessibleIntent =
                new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(mAccessibleIntent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
