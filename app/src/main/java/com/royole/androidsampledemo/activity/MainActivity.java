package com.royole.androidsampledemo.activity;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.royole.androidsampledemo.R;
import com.royole.androidsampledemo.adapter.MainAdapter;
import com.royole.settingspreference.activity.SettingsActivity;
import com.royole.settingspreference.activity.SettingsMainActivity;
import com.royole.settingspreference.fragment.CommentSettingsFragment;
import com.royole.settingspreference.fragment.GeneralSettingsFragment;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    protected int[] mDataset;
    private static final int DATASET_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataset();
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MainAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

   public void onClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.tosettings:
                intent = new Intent(this,SettingsMainActivity.class);
                startActivity(intent);
            break;
            case R.id.pendingactivity:
                intent = new Intent(this,PendingIntentActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
   }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
//        mDataset = new String[DATASET_COUNT];
//        for (int i = 0; i < DATASET_COUNT; i++) {
//            mDataset[i] = "This is element #" + i;
//        }
        mDataset = new int[DATASET_COUNT];
        mDataset[0] = R.drawable.ic_business_black_24dp;
        mDataset[1] = R.drawable.ic_call_black_24dp;
        mDataset[2] = R.drawable.ic_chat_black_24dp;
    }
}
