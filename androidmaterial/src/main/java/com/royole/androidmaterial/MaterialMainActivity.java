package com.royole.androidmaterial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.royole.androidmaterial.adapter.MaterialAdapter;

public class MaterialMainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        String[] myDataset = {
                "GeneralSettingsFragment",
                "CommentSettingFragment",
                "XmlPreferenceFragment",
                "CodePreferenceFragment"
        };

        // specify an adapter (see also next example)
        mAdapter = new MaterialAdapter(this,myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onClick(View view){
        int i = view.getId();
        if (i == R.id.add) {
        } else if (i == R.id.update) {
        } else if (i == R.id.delete) {
        }
    }
}
