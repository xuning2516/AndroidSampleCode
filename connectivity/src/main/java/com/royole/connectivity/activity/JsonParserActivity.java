package com.royole.connectivity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.royole.connectivity.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class JsonParserActivity extends AppCompatActivity {
    private String jsonString = "[{\"id\":\"5\",\"version\":\"5.5\",\"name\":\"Clash of Clans\"},\n" +
            "{\"id\":\"6\",\"version\":\"55\",\"name\":\"Clash of Australia\"},\n" +
            "{\"id\":\"7\",\"version\":\"75\",\"name\":\"Clash of Royole\"}]";

    private TextView mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parse);
        mInfo = findViewById(R.id.info);
    }

    public void onClick(View view){
        int viewId = view.getId();
        if(R.id.simple == viewId){
            parseJSONWithJSONObject(jsonString);
        }else if(R.id.normal == viewId){

        }else if(R.id.create == viewId){
            createJSONArray();
        }
    }
    private void parseJSONWithJSONObject(String jsonData) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("MainActivity", "id is " + id);
                Log.d("MainActivity", "name is " + name);
                Log.d("MainActivity", "version is " + version);
                stringBuilder.append("id: " + id + "\n");
                stringBuilder.append("name: " + name + "\n");
                stringBuilder.append("version: " + version + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mInfo.setText(stringBuilder.toString());
    }

    public void createJSONArray(){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2= new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        try {
            jsonObject1.put("id", "2");
            jsonObject1.put("name", "nixu");
            jsonObject1.put("version", "1.0");

            jsonObject2.put("id", "3");
            jsonObject2.put("name", "royole");
            jsonObject2.put("version", "3.0");

            jsonObject3.put("id", "5");
            jsonObject3.put("name", "nubia");
            jsonObject3.put("version", "5.5");

            jsonArray.put(jsonObject1);
            jsonArray.put(jsonObject2);
            jsonArray.put(jsonObject3);
        }catch (JSONException ex){
            ex.printStackTrace();
        }

        jsonString = jsonArray.toString();
        mInfo.setText("create \n"+jsonArray.toString());
    }
}
