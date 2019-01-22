package com.royole.storageaccess.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.royole.storageaccess.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InnerFileActivity extends AppCompatActivity {
    String filename = "myfile";
    String fileContents = "Hello world!";
    FileOutputStream outputStream;
    FileInputStream inputStream;
    BufferedReader reader = null;
    StringBuilder content = null;
    private TextView mInfo;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);
        mContext = this;
        mInfo = findViewById(R.id.info);
    }

    public void onClick(View view){
        int viewId = view.getId();
        if(R.id.read == viewId){
            content = new StringBuilder();
            try {
                inputStream = openFileInput(filename);
                reader = new BufferedReader((new InputStreamReader(inputStream)));
                String line = "";
                while ((line = reader.readLine()) != null){
                    content.append(line);
                }

                mInfo.setText(content.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(reader!=null){
                    try {
                        reader.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }else if(R.id.write == viewId){
            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(fileContents.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(R.id.dirs == viewId){
            content = new StringBuilder();
            File filedir = getFilesDir();
            File namedir = getDir("royole",MODE_PRIVATE);
            File cachedir = getCacheDir();
            content.append("getFilesDir: " + filedir.getAbsolutePath() + "\n");
            content.append("getDir royole private: " + namedir.getAbsolutePath() + "\n");
            content.append("getCacheDir: " + cachedir.getAbsolutePath() + "\n");

            File directory = getFilesDir();
            File namefile = new File(directory, filename);

            File externaldir = getExternalFilesDir(null);
            File externaldir2 = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

            File externalcachedir = getExternalCacheDir();
            File[] externalmediadir = getExternalMediaDirs();

            content.append("getExternalFilesDir: " + externaldir.getAbsolutePath() + "\n");
            content.append("getExternalFilesDir picture: " + externaldir2.getAbsolutePath() + "\n");
            content.append("getExternalCacheDir: " + externalcachedir.getAbsolutePath() + "\n");
            for(File file1:externalmediadir) {
                content.append("externalmediadir: " + file1.getAbsolutePath() + "\n");
            }

            mInfo.append(content.toString());
        }else if(R.id.del == viewId){
            File royolefile = new File(getFilesDir(),"myfile");
           royolefile.delete();

           mContext.deleteFile("myfile"); //删除files目录下的文件

        }else if(R.id.query == viewId){
            content = new StringBuilder();
            boolean success = false;
            File royole = new File("myfile");
            try{
                success = royole.createNewFile();
                content.append("myfile create :"+ success +"\n");
            }catch (IOException ex){
                //ex.printStackTrace();
            }

            long freespace = royole.getFreeSpace(); //in bytes
            long totalspace = royole.getTotalSpace();
            content.append("file:"+ royole.getAbsolutePath()+"\n");
            content.append(" freespace: "+freespace+" bytes");
            content.append(" totolspace: "+totalspace+" bytes\n");


            File royolefile = new File(getFilesDir(),"myfile");
            try{
                success = royolefile.createNewFile();
                content.append("myfile create :"+ success +"\n");
            }catch (IOException ex){
                ex.printStackTrace();
            }


            long royolefilefreespace = royolefile.getFreeSpace(); //in bytes 必须文件存在才能得到值
            long royolefiletotalspace = royolefile.getTotalSpace();

            content.append("file:"+ royolefile.getAbsolutePath()+"\n");
            content.append(" freespace: "+royolefilefreespace/1024+" MB");
            content.append(" totolspace: "+royolefiletotalspace/1024 +" MB\n");

            File royoleextfile = new File(getExternalFilesDir(null),"myfile");
            try{
                success = royoleextfile.createNewFile();
                content.append("myfile create :"+ success +"\n");
            }catch (IOException ex){
                ex.printStackTrace();
            }

            long royoleextfreespace = royoleextfile.getFreeSpace(); //in bytes
            long royoleexttotalspace = royoleextfile.getTotalSpace();

            content.append("file:"+ royoleextfile.getAbsolutePath()+"\n");
            content.append(" freespace: "+royoleextfreespace);
            content.append(" totolspace: "+royoleexttotalspace+"\n");

            mInfo.append(content.toString());



        }else if(R.id.createcachetmp == viewId){
            content = new StringBuilder();
            String url = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            File file = null;
            try {
                String fileName = Uri.parse(url).getLastPathSegment();
                file = File.createTempFile(fileName, null, getCacheDir());
            } catch (IOException e) {
                // Error while creating file
            }

            content.append((file == null )?"":file.getAbsolutePath() +"\n");
            mInfo.append(content.toString());
        }else if(R.id.contextfile == viewId){
            String[] files = fileList();
            deleteFile("myfile");
            getResources().openRawResource(R.raw.myraw);
        }
    }
}
