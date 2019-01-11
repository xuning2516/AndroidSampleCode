package com.royole.views.activity.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.royole.views.R;

import java.util.ArrayList;

public class TranscriptListActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    private ListView mListView;
    private EditText mUserText;

    private ArrayAdapter<String> mAdapter;

    private ArrayList<String> mStrings = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcript_list);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings);
        mListView = findViewById(R.id.list);
        mListView.setAdapter(mAdapter);

        mUserText = (EditText) findViewById(R.id.userText);

        mUserText.setOnClickListener(this);
        mUserText.setOnKeyListener(this);
    }

    public void onClick(View v) {
        sendText();
    }

    private void sendText() {
        String text = mUserText.getText().toString();
        mAdapter.add(text);
        mUserText.setText(null);
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_CENTER:
                case KeyEvent.KEYCODE_ENTER:
                    sendText();
                    return true;
            }
        }
        return false;
    }
}
