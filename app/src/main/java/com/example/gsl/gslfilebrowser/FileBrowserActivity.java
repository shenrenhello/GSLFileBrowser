package com.example.gsl.gslfilebrowser;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileBrowserActivity extends ListActivity {

    private FileSort fileSort;
    private List<File> fileList;
    private ArrayAdapter<Efile> adapter;
    private TextView pathTitle;
    public static final String ROOT_DIR = "/storage/emulated/0/";
    private File dirFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        fileList = new ArrayList<File>();
        pathTitle = (TextView) findViewById(R.id.path_title);
        configAdaper();


    }
    private void configAdaper(){

        String dirName = getIntent().getStringExtra("DirName");
        if (dirName == null){
            dirName = ROOT_DIR;
        }
        pathTitle.setText(dirName);

        dirFile = new File(dirName);

        File[] children = dirFile.listFiles();
        //sort begin
        for (int i = 0;i<children.length;i++){
            fileList.add(children[i]);
        }
        fileSort = new FileSort(FileSort.SORT_BY_NAME);
        fileSort.sortListFiles(fileList);
        //sort end
        adapter = new ArrayAdapter<Efile>(this,android.R.layout.simple_list_item_1);
        if (children != null){
            for (File file:fileList){
                adapter.add(new Efile(file));
            }
            setListAdapter(adapter);
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Efile ef = adapter.getItem(position);
        if (ef.getFile().isDirectory()){
            Intent intent = new Intent(this,FileBrowserActivity.class);
            intent.putExtra("DirName",ef.getFile().getAbsolutePath());
            startActivity(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }



}
