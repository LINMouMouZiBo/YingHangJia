package com.yinghangjiaclient.more;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.kayvannj.permission_utils.Func;
import com.github.kayvannj.permission_utils.PermissionUtil;
import com.orhanobut.logger.Logger;
import com.yinghangjiaclient.R;

public class MoreMainActivity extends AppCompatActivity {

    private static final int CODE_FOR_WRITE_PERMISSION = 001;
    private static final int REQUEST_CODE_STORAGE = 2;
    private PermissionUtil.PermissionRequestObject mStoragePermissionRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.init("ying");
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.more_first);

            // 跳转到新手指引
            Button personal_center_btn = (Button) findViewById(R.id.button9);
            personal_center_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent1 = new Intent();
//                    intent1.setClass(MoreMainActivity.this,
//                            LearnerActivity.class);
//                    startActivity(intent1);
                    String url = "http://119.29.135.223:8000/video.html"; // web address
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });

            // 跳转到反馈中心
            Button feelback_btn = (Button) findViewById(R.id.button10);
            feelback_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent();
                    intent1.setClass(MoreMainActivity.this,
                            FeelbackActivity.class);
                    startActivity(intent1);
                }
            });

            // 跳转到联系我们
            Button contact_btn = (Button) findViewById(R.id.button11);
            contact_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent();
                    intent1.setClass(MoreMainActivity.this,
                            ConnectUsActivity.class);
                    startActivity(intent1);
                }
            });

            // 跳转到分享
            Button share_btn = (Button) findViewById(R.id.button12);
            share_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        requestContactPermission();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger.e(e.getMessage());
                        Toast.makeText(getApplicationContext(),
                                "无法打开权限授权表，请手动赋予应用“读写外部存储”的权限", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // 跳转到关于我们
            Button about_us_btn = (Button) findViewById(R.id.button13);
            about_us_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent();
                    intent1.setClass(MoreMainActivity.this,
                            AboutUsActivity.class);
                    startActivity(intent1);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
    }

    private void requestContactPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请 WRITE_CONTACTS 权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CODE_FOR_WRITE_PERMISSION);
        } else {
            share();
        }
    }

  
    
}
