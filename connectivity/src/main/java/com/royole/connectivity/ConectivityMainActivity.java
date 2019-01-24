package com.royole.connectivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.royole.connectivity.activity.GsonParserActivity;
import com.royole.connectivity.activity.JsonParserActivity;
import com.royole.connectivity.activity.XmlParserActivity;

public class ConectivityMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conectivity_main);
    }

    public void onClick(View view){
        Intent intent = null;
        int viewId = view.getId();
        if(R.id.xml == viewId){
            intent = new Intent(this, XmlParserActivity.class);
            startActivity(intent);
        }else if(R.id.json == viewId){
            intent = new Intent(this, JsonParserActivity.class);
            startActivity(intent);
        }else if(R.id.gson == viewId){
            intent = new Intent(this, GsonParserActivity.class);
            startActivity(intent);
        }
    }
}
