package com.example.gsl.gslfilebrowser;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by dell on 2017/12/22.
 */

public class MainActivity extends Activity {
    Button btn;
    //permissions
    public static final String[] allPermissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btnToFileBrowser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this,FileBrowserActivity.class);
                startActivity(i);
            }
        });
        btn.setClickable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions(allPermissions);
    }

    private void checkPermissions(String... permissions){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for (String p : permissions){
                int i = ContextCompat.checkSelfPermission(this,p);
                if (i != PackageManager.PERMISSION_GRANTED){
                    startRequestPermission();
                    return;//有一个不满足就return,去请求
                }
            }
        }
        btn.setClickable(true);
    }
    /***
     * request permission
     */
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this,allPermissions,123);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"权限获取成功",Toast.LENGTH_SHORT).show();
                }else {
                    //判断用户是否点击了不再提醒。
                    boolean b = shouldShowRequestPermissionRationale(allPermissions[0]) || shouldShowRequestPermissionRationale(allPermissions[1]);
                    if (!b){
                        //提醒用户去设置界面打开权限
                        Toast.makeText(this,getApplicationInfo().loadLabel(getPackageManager()).toString()+
                                "权限被拒绝，您可以一次选择“设置>应用”来更改权限",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(this,"权限被拒绝",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        }
    }
}
