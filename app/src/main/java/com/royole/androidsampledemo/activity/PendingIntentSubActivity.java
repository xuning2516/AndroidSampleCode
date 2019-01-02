package com.royole.androidsampledemo.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.royole.androidsampledemo.R;
import com.royole.androidsampledemo.service.PendingIntentService;

public class PendingIntentSubActivity extends AppCompatActivity {
    private static final String TAG = "PendingIntentSub";

    public static final String PENDING__RESULT = "pd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_intent_sub);
        Log.d(TAG, "onCreate: ");
        setResult(200,new Intent().putExtra("data", "activity返回数据"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    public void pendingIntentSend(View view){
        PendingIntent pendingIntent =
                getIntent().getParcelableExtra(PendingIntentActivity.PENDING__RESULT);
        try {
            pendingIntent.send(PendingIntentSubActivity.this, 101,
                new Intent().putExtra("data", "service返回数据"));
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }



}
