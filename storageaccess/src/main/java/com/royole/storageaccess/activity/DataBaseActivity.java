package com.royole.storageaccess.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.royole.storageaccess.R;
import com.royole.storageaccess.database.RyDatabaseHelper;

public class DataBaseActivity extends AppCompatActivity {
    private TextView mInfo;
    private Context mContext;

    private RyDatabaseHelper ryDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        mContext = this;
        mInfo = findViewById(R.id.info);
        ryDatabaseHelper = new RyDatabaseHelper(mContext,"rycloth.db",null,1);
    }

    public void onClick(View view){
        int viewId = view.getId();
        if(R.id.create == viewId){
            SQLiteDatabase database = ryDatabaseHelper.getWritableDatabase();
        }else if(R.id.insert == viewId){
            SQLiteDatabase db = ryDatabaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            // 开始组装第一条数据
            values.put("name", "The Da Vinci Code");
            values.put("author", "Dan Brown");
            values.put("pages", 454);
            values.put("price", 16.96);
            db.insert("Book", null, values); // 插入第一条数据
            values.clear();
            // 开始组装第二条数据
            values.put("name", "The Lost Symbol");
            values.put("author", "Dan Brown");
            values.put("pages", 510);
            values.put("price", 19.95);
            db.insert("Book", null, values); // 插入第二条数据
        }else if(R.id.update == viewId){
            SQLiteDatabase db = ryDatabaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", 10.99);
            db.update("Book", values, "name = ?", new String[] { "The Da Vinci Code" });
        }else if(R.id.delete == viewId){
            SQLiteDatabase db = ryDatabaseHelper.getWritableDatabase();
            db.delete("Book", "pages > ?", new String[] { "500" });

        }else if(R.id.query == viewId){
            SQLiteDatabase db = ryDatabaseHelper.getReadableDatabase();
            StringBuilder content = new StringBuilder();
            // 查询Book表中所有的数据
            Cursor cursor = db.query("Book", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    Log.d("MainActivity", "book name is " + name);
                    Log.d("MainActivity", "book author is " + author);
                    Log.d("MainActivity", "book pages is " + pages);
                    Log.d("MainActivity", "book price is " + price);
                    content.append("name: "+name+"\n");
                    content.append("author: "+author+"\n");
                    content.append("pages: "+pages+"\n");
                    content.append("price: "+price+"\n");
                } while (cursor.moveToNext());
                mInfo.setText("table Book:\n" + content.toString());
            }
            cursor.close();
        }
    }
}
