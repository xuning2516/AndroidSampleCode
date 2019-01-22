package com.royole.datashare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DataShareMainActivity extends AppCompatActivity {
    private static final String TAG = "DataShareMainActivity";
    private TextView mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_share_main);
        mInfo = findViewById(R.id.info);
    }

    public void onClick(View view){
        int viewId = view.getId();
        Intent intent = null;
        if(R.id.fileshare == viewId){
            intent = new Intent(this,FileShareActivity.class);
            startActivity(intent);
        }else if(R.id.contentprovidershare == viewId){
            intent = new Intent(this,ContentProviderShareActivity.class);
            startActivity(intent);
        }else if(R.id.intentshare == viewId){
            intent = new Intent(this,IntentShareActivity.class);
            startActivity(intent);
        }
    }
}
