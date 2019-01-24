package com.royole.connectivity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.royole.connectivity.App;
import com.royole.connectivity.R;

import java.util.List;

public class GsonParserActivity extends AppCompatActivity {
    private String jsonString = "[{\"id\":\"5\",\"version\":\"5.5\",\"name\":\"Clash of Clans\"},\n" +
            "{\"id\":\"6\",\"version\":\"55\",\"name\":\"Clash of Australia\"},\n" +
            "{\"id\":\"7\",\"version\":\"75\",\"name\":\"Clash of Royole\"}]";

    private TextView mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_parser);
        mInfo = findViewById(R.id.info);
    }


    public void onClick(View view){
        int viewId = view.getId();
        if(R.id.simple == viewId){
            parseJSONWithGSON(jsonString);
        }else if(R.id.normal == viewId){

        }
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        StringBuilder content = new StringBuilder();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>() {}.getType());
        for (App app : appList) {
            Log.d("MainActivity", "id is " + app.getId());
            Log.d("MainActivity", "name is " + app.getName());
            Log.d("MainActivity", "version is " + app.getVersion());
            content.append("id: "+app.getId()+"\n");
            content.append("getName: "+app.getName()+"\n");
            content.append("getVersion: "+app.getVersion()+"\n");
            content.append("\n");
        }
        mInfo.setText(content.toString());
    }
}
