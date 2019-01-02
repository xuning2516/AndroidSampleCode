package com.royole.settingspreference.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.royole.settingspreference.MyAdapter;
import com.royole.settingspreference.R;
import com.royole.settingspreference.fragment.GeneralSettingsFragment;

public class SettingsMainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_main);
        mRecyclerView = findViewById(R.id.ryview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        String[] myDataset = {
                "GeneralSettingsFragment",
                "CommentSettingFragment",
                "XmlPreferenceFragment"
        };

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this,myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }
}
