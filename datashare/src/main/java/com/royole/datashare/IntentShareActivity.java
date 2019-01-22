package com.royole.datashare;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class IntentShareActivity extends AppCompatActivity {
    private static final String TAG = "IntentShareActivity";
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_share);
    }
    
    public void onClick(View view){
        int viewId = view.getId();
        Intent intent = null;
        if(R.id.sendshare == viewId){
            intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            intent.setType("text/plain");
            startActivity(intent);
        }else if(R.id.choosershare == viewId){
            intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, getResources().getText(R.string.send_to)));
        }else if(R.id.shareactionprovider == viewId){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.intent_share_menu, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent myShareIntent = new Intent(Intent.ACTION_SEND);
        myShareIntent.setType("image/*");
        //myShareIntent.putExtra(Intent.EXTRA_STREAM, myImageUri);

        mShareActionProvider.setShareIntent(myShareIntent);

        // Return true to display menu
        return true;

    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
