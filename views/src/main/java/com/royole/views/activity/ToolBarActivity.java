package com.royole.views.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.royole.views.R;

public class ToolBarActivity extends AppCompatActivity {
    private static final String TAG = "ToolBarActivity";
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_toolbar);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: ",new RuntimeException("here").fillInStackTrace());
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.backup) {
            Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.delete) {
            Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
        } else if (i == R.id.settings) {
            Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void invalidateOptionsMenu() {
        Log.d(TAG, "invalidateOptionsMenu: ",new RuntimeException("here").fillInStackTrace());
        super.invalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(TAG, "onPrepareOptionsMenu: ",new RuntimeException("here").fillInStackTrace());
        return super.onPrepareOptionsMenu(menu);
    }
}
