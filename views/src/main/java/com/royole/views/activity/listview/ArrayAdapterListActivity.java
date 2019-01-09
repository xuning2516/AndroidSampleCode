package com.royole.views.activity.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.royole.views.Cheeses;
import com.royole.views.R;

public class ArrayAdapterListActivity extends AppCompatActivity {
    private String[] mStrings = Cheeses.sCheeseStrings;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter_list);
        mListView = findViewById(R.id.list);
        // Use an existing ListAdapter that will map an array
        // of strings to TextViews


        mListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings));
        mListView.setTextFilterEnabled(true);
    }



}
