package com.royole.androidsampledemo.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.royole.androidsampledemo.activity.PendingIntentActivity;

public class PendingIntentService extends Service {
    public PendingIntentService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.e("xxxxx", "PendingIntentService onStart");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PendingIntent pendingIntent = intent
                        .getParcelableExtra(PendingIntentActivity.PENDING__RESULT);
                try {
                    pendingIntent.send(PendingIntentService.this, 101,
                            new Intent().putExtra("data", "service返回数据"));
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        }, 3000);
        return START_STICKY;
    }
}
