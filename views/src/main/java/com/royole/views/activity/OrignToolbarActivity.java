package com.royole.views.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.royole.views.R;

public class OrignToolbarActivity extends Activity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orign_toolbar);
//        mToolbar = findViewById(R.id.toolbar);
//        setActionBar(mToolbar);
    }
}
