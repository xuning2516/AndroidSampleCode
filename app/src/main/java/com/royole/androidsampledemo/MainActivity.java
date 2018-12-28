package com.royole.androidsampledemo;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.royole.settingspreference.activity.SettingsActivity;
import com.royole.settingspreference.fragment.CommentSettingsFragment;
import com.royole.settingspreference.fragment.GeneralSettingsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

   public void onClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.tosettings:
                intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
            break;
        }
   }
}
