package com.royole.fourcomponent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.royole.fourcomponent.provider.ProviderTestActivity;

public class FourMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_main);
    }

    public void onClick(View view){
        int viewId = view.getId();
        Intent intent = null;
        if(R.id.provider == viewId){
            intent = new Intent(this, ProviderTestActivity.class);
            startActivity(intent);
        }
    }
}
