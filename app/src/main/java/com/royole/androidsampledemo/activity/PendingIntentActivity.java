package com.royole.androidsampledemo.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.royole.androidsampledemo.R;
import com.royole.androidsampledemo.service.PendingIntentService;

public class PendingIntentActivity extends AppCompatActivity {
    private static final String TAG = "PendingIntentActivity";

    public static final String PENDING__RESULT = "pd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_intent);
        Log.d(TAG, "onCreate: ");
        PendingIntent pendingResult = createPendingResult(100, new Intent(), 0);
        Intent i = new Intent(this, PendingIntentSubActivity.class);
        i.putExtra(PENDING__RESULT, pendingResult);
        startActivityForResult(i,101);

//        startService(i);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
                Log.e("xxxxx",
                        "onActivityResult" + " requestCode = " + requestCode
                                + ",resultCode = " + resultCode + ",data = "
                                + data.getStringExtra("data"));
                break;
            case 101:
                Log.e("xxxxx",
                        "onActivityResult" + " requestCode = " + requestCode
                                + ",resultCode = " + resultCode );
                break;
        }

    }

}
