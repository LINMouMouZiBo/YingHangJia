package com.yinghangjiaclient.recommend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.yinghangjiaclient.R;

import java.io.File;
import java.net.URISyntaxException;

public class ProduceBuyActivity extends AppCompatActivity {
    private String bankName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.init("ying");
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.payment);

            Intent intent = this.getIntent();
            if (intent != null) {
                bankName = intent.getStringExtra("bankName");
            }

            giveMsg();

            Button buy_Btn = (Button) findViewById(R.id.button18);
            buy_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giveMsg();
                }
            });

            Button backBtn = (Button) findViewById(R.id.button3);
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            Button appointment = (Button) findViewById(R.id.appointment);
            appointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] items = {"百度地图", "高德地图", "腾讯地图（网页版）"};
                    new AlertDialog.Builder(ProduceBuyActivity.this).setTitle("选择以下方式导航").setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                openBaiduMap();
                            } else if (which == 1) {
                                openGaoDeMap();
                            } else {
                                openTencentMap();
                            }
                            dialog.dismiss();
                        }
                    }).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
    }

    private void giveMsg() {
        Toast.makeText(getApplicationContext(),
                "购买系统尚未上线，敬请期待", Toast.LENGTH_SHORT).show();
    }

    private void openTencentMap() {
        try {
            String url = "http://apis.map.qq.com/uri/v1/search?keyword=" + bankName + "&center=CurrentLocation&radius=100000&referer=myapp"; // web address
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
    }

    private void openGaoDeMap() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW",
                    Uri.parse("androidamap://keywordNavi?sourceApplication=赢行家&keyword=" + bankName + "&style=2"));
            if (isInstallByread("com.autonavi.minimap")) {
                intent.setPackage("com.autonavi.minimap");
                //启动调用
                startActivity(intent); 
            } else {
                Toast.makeText(ProduceBuyActivity.this,
                        "没有安装高德地图客户端", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
    }

    private void openBaiduMap() {
        try {
            Intent intent = Intent.getIntent("intent://map/geocoder?address=" + bankName + "&src=thirdapp.geo.yourCompanyName.yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            if (isInstallByread("com.baidu.BaiduMap")) {
                //启动调用
                startActivity(intent);
            } else {
                Toast.makeText(ProduceBuyActivity.this,
                        "没有安装百度地图客户端", Toast.LENGTH_SHORT).show();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
    }

    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

}
