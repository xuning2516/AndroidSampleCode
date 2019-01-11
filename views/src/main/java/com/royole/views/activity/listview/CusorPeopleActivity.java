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
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.royole.androidcommon.utils.PermissionUtils;
import com.royole.views.R;

import java.util.Arrays;


public class CusorPeopleActivity extends AppCompatActivity {
    private static final  int REQUEST_CODE_PERMISSIONS = 0x001;

    private Context mContext;


    private ListView mListView;

    private final String[] PERMISSIONS = new String[]{Manifest.permission.READ_CONTACTS
            , Manifest.permission.WRITE_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusor_people);
        mListView = findViewById(R.id.list);
        mContext = this;

        initCheckPermissions();
    }

    public void initDataAndView(){
        // Get a cursor with all people
        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                CONTACT_PROJECTION, null, null, null);
        startManagingCursor(c);


        ListAdapter adapter = new SimpleCursorAdapter(this,
                // Use a template that displays a text view
                android.R.layout.simple_list_item_1,
                // Give the cursor to the list adatper
                c,
                // Map the NAME column in the people database to...
                new String[] {ContactsContract.Contacts.DISPLAY_NAME},
                // The "text1" view defined in the XML template
                new int[] {android.R.id.text1},
                CursorAdapter.FLAG_AUTO_REQUERY);
        mListView.setAdapter(adapter);
    }

    private static final String[] CONTACT_PROJECTION = new String[] {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
    };

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
                                    PermissionUtils.requestPermissions(CusorPeopleActivity.this, permission, REQUEST_CODE_PERMISSIONS);
                                }
                            });
                        }

                        @Override
                        public void onDeniedDontAsk(String... permission) {
                            PermissionUtils.requestPermissions(CusorPeopleActivity.this, permission, REQUEST_CODE_PERMISSIONS);
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
        new AlertDialog.Builder(CusorPeopleActivity.this)
                .setTitle("申请权限")
                .setMessage("我们需要" + Arrays.toString(permission)+"权限")
                .setPositiveButton("确定", onClickListener)
                .show();
    }
}
