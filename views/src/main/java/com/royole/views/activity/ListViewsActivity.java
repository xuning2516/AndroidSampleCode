package com.royole.views.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class ListViewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_views);
    }


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
}
