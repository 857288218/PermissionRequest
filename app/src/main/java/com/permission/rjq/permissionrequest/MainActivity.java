package com.permission.rjq.permissionrequest;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import com.permission.rjq.permission.PermissionListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.permission.rjq.permission.PermissionRequest permissionRequest = new com.permission.rjq.permission.PermissionRequest(this);
        permissionRequest.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new PermissionListener() {
            @Override
            public void onGranted() {
                //获取权限后的操作
                Toast.makeText(MainActivity.this, "您获得了权限", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                //禁止权限后的操作
                Toast.makeText(MainActivity.this, "您禁止了权限", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShouldShowRationale(List<String> deniedPermission) {
                //禁止权限并且选择了不再提示的操作
                Toast.makeText(MainActivity.this, "您禁止并且选择了不再提示权限", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
