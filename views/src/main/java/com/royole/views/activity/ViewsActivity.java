package com.royole.views.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.royole.views.R;
import com.royole.views.activity.listview.ActivateItemActivity;
import com.royole.views.activity.listview.ArrayAdapterListActivity;
import com.royole.views.activity.listview.ArrayOverlayActivity;
import com.royole.views.activity.listview.BorderSelectActivity;
import com.royole.views.activity.listview.CollapsedListActivity;
import com.royole.views.activity.listview.CusorPeopleActivity;
import com.royole.views.activity.listview.CusorPhoneActivity;
import com.royole.views.activity.listview.EfficientAdapterActivity;
import com.royole.views.activity.listview.ItemSelectListActivity;
import com.royole.views.activity.listview.ListAdpaterActivity;
import com.royole.views.activity.listview.MultiChoiceListActivity;
import com.royole.views.activity.listview.PhotosActivity;
import com.royole.views.activity.listview.SelectionModeActivity;
import com.royole.views.activity.listview.SeparatorsActivity;
import com.royole.views.activity.listview.SingleChoiceListActivity;
import com.royole.views.activity.listview.SlowAdapterActivity;
import com.royole.views.activity.listview.TranscriptListActivity;
import com.royole.views.adapter.ListViewArrayAdapter;

public class ViewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_views);
        Intent intent = getIntent();
        String viewName = intent.getStringExtra("viewName").toLowerCase();
        int id = getResources().getIdentifier("layout_"+viewName, "layout", getPackageName());



        Intent activityIntent = null;
        switch (viewName){
            case "listview":
                setContentView(id);
                initListView();
                break;
            case "buttons":
                setContentView(id);
                initButtons();
                break;
            case "toolbar":

                setContentView(id);
                initToolbar();
                break;
            case "webview":
                activityIntent = new Intent(this,WebViewActivity.class);
                startActivity(activityIntent);
                break;
        }

    }

    //TODO::move to Fragment
    public void initListView(){
        String[] myStringArray={
                "array",
                "cusor_people",
                "cusor_phones",
                "listadapter",
                "separators",
                "ListAdapter Collapsed",
                "cusor_phones2",
                "photos",
                "array_overlay",
                "single_choice_list",
                "multi_choice_list",
                "transcript",
                "slow_adapter",
                "efficient_adapter",
                "selection_mode",
                "border_selection_mode",
                "active_items",
        };
        final Class[] targetClass = {
                ArrayAdapterListActivity.class,
                CusorPeopleActivity.class,
                CusorPhoneActivity.class,
                ListAdpaterActivity.class,
                SeparatorsActivity.class,
                CollapsedListActivity.class,
                ItemSelectListActivity.class,
                PhotosActivity.class,
                ArrayOverlayActivity.class,
                SingleChoiceListActivity.class,
                MultiChoiceListActivity.class,
                TranscriptListActivity.class,
                SlowAdapterActivity.class,
                EfficientAdapterActivity.class,
                SelectionModeActivity.class,
                BorderSelectActivity.class,
                ActivateItemActivity.class
        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, myStringArray);
        ListViewArrayAdapter<String> adapter = new ListViewArrayAdapter<String>(this,
                R.layout.fruit_item, myStringArray);
        ListView listView = (ListView) findViewById(R.id.view_listview);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ViewsActivity.this, position+ ":", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(),targetClass[position]);
                startActivity(intent);
            }
        });
    }

    public void initButtons(){

    }

    public void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onButtionClick(View view){
        int i = view.getId();
        if (i == R.id.floatingActionButton) {
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ViewsActivity.this, "snackaction", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        }else if(i == R.id.floatingActionButton){

        }
    }

}
