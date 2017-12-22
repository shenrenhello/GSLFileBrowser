package com.example.gsl.gslfilebrowser;

import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.File;

public class FileBrowserActivity extends ListActivity {

    private ArrayAdapter<Efile> adapter;
    File dirFile = new File("/storage/emulated/0/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        configAdaper();
    }
    private void configAdaper(){

        File[] children = dirFile.listFiles();

        adapter = new ArrayAdapter<Efile>(this,android.R.layout.simple_list_item_1);
        if (children != null){
            for (File file:children){
                adapter.add(new Efile(file));
            }
            setListAdapter(adapter);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();


    }



}
