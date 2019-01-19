package com.royole.storageaccess.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.royole.storageaccess.R;

public class SharePreferencesActivity extends AppCompatActivity {
    private Context mContext;
    private TextView mInfo;
    private EditText meditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);
        mContext = this;
        meditText = findViewById(R.id.editText);
        mInfo = findViewById(R.id.info);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        SharedPreferences defaultAppPreference = PreferenceManager.getDefaultSharedPreferences(mContext);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            String defaultpreferName = PreferenceManager.getDefaultSharedPreferencesName(mContext);
            mInfo.append("default pref name "+ defaultpreferName);
        }
        SharedPreferences.Editor deditor = defaultAppPreference.edit();
        int score = 123;
        deditor.putInt("score_key", score);
        deditor.commit();

        SharedPreferences namePreferences = mContext.getSharedPreferences("my_prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = namePreferences.edit();

        editor.putInt("score_key", score);
        editor.commit();

    }

    public void onClick(View view){
        int id = view.getId();
        if(R.id.read == id){
            SharedPreferences sharedPref = ((Activity)mContext).getPreferences(Context.MODE_PRIVATE);
            int score = sharedPref.getInt("score_key",-1);
            mInfo.append("read score_key "+ score+"\n");
        }else if(R.id.save == id){
            SharedPreferences sharedPref = ((Activity)mContext).getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            int score = Integer.valueOf(meditText.getText().toString());
            editor.putInt("score_key", score);
            editor.commit();
            meditText.setText("");
        }

    }
}
