package com.royole.views.activity.listview;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.royole.androidcommon.utils.PermissionUtils;
import com.royole.views.R;

import java.util.Arrays;

public class ItemSelectListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener{
    private static final String TAG = "ItemSelectListActivity";

    private TextView mPhone;
    private ListView mListView;

    private Context mContext;
    private static final  int REQUEST_CODE_PERMISSIONS = 0x002;
    private final String[] PERMISSIONS = new String[]{Manifest.permission.READ_CONTACTS
            , Manifest.permission.WRITE_CONTACTS};

    private static final String[] PHONE_PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.TYPE,
            ContactsContract.CommonDataKinds.Phone.LABEL,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
    };

    private static final int COLUMN_PHONE_TYPE = 1;
    private static final int COLUMN_PHONE_LABEL = 2;
    private static final int COLUMN_PHONE_NUMBER = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_select_list);
        mContext = this;
        mListView = findViewById(R.id.list);
        mPhone = (TextView) findViewById(R.id.phone);
        mListView.setOnItemSelectedListener(this);
//        mListView.setOnItemClickListener(this);
        mListView.setItemsCanFocus(false);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        initCheckPermissions();
    }

    private void initCheckPermissions(){
        if(Build.VERSION.SDK_INT >= 23) { //android M only
            PermissionUtils.checkPermissions(mContext,
                    new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS},
                    new PermissionUtils.PermissionCheckCallBack() {
                        @Override
                        public void onGranted() {
                            //onHasPermissiontoCamera();
                            initDataAndView();
                        }

                        @Override
                        public void onDenied(final String... permission) {
                            showExplainDialog(permission, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    PermissionUtils.requestPermissions(mContext, permission, REQUEST_CODE_PERMISSIONS);
                                }
                            });
                        }

                        @Override
                        public void onDeniedDontAsk(String... permission) {
                            PermissionUtils.requestPermissions(mContext, permission, REQUEST_CODE_PERMISSIONS);
                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        PermissionUtils.onRequestPermissionsResult(mContext, PERMISSIONS, grantResults, new PermissionUtils.PermissionCheckCallBack() {
            @Override
            public void onGranted() {
                // toCamera();
                initDataAndView();
            }

            @Override
            public void onDenied(String... permission) {
                Toast.makeText(mContext, "我们需要"+Arrays.toString(permission)+"权限", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeniedDontAsk(String... permission) {
                Toast.makeText(mContext, "我们需要"+Arrays.toString(permission)+"权限", Toast.LENGTH_SHORT).show();
                PermissionUtils.toAppSetting(mContext);
            }
        });
    }

    /**
     * 解释权限的dialog
     *
     */
    private void showExplainDialog(String[] permission, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(mContext)
                .setTitle("申请权限")
                .setMessage("我们需要" + Arrays.toString(permission)+"权限")
                .setPositiveButton("确定", onClickListener)
                .show();
    }

    private void initDataAndView(){
        // Get a cursor with all numbers.
        // This query will only return contacts with phone numbers
        Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PHONE_PROJECTION, ContactsContract.CommonDataKinds.Phone.NUMBER + " NOT NULL", null, null);
        startManagingCursor(c);

        ListAdapter adapter = new SimpleCursorAdapter(this,
                // Use a template that displays a text view
                android.R.layout.simple_list_item_1,
                // Give the cursor to the list adapter
                c,
                // Map the DISPLAY_NAME column to...
                new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                // The "text1" view defined in the XML template
                new int[] {android.R.id.text1});
        mListView.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        Log.d(TAG, "onItemSelected: ");
        if (position >= 0) {
            //Get current cursor
            Cursor c = (Cursor) parent.getItemAtPosition(position);
            int type = c.getInt(COLUMN_PHONE_TYPE);
            String phone = c.getString(COLUMN_PHONE_NUMBER);
            String label = null;
            //Custom type? Then get the custom label
            if (type == ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM) {
                label = c.getString(COLUMN_PHONE_LABEL);
            }
            //Get the readable string
            String numberType = (String) ContactsContract.CommonDataKinds.Phone.getTypeLabel(getResources(), type, label);
            String text = numberType + ": " + phone;
            Log.d(TAG, "onItemSelected: "+ text);
            mPhone.setText(text);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, "onNothingSelected: ");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick: ");
    }
}
