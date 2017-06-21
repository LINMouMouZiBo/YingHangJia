package com.yinghangjiaclient.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.koushikdutta.ion.Ion;
import com.orhanobut.logger.Logger;
import com.yinghangjiaclient.R;
import com.yinghangjiaclient.login.LoginActivity;
import com.yinghangjiaclient.login.RegisterActivity;
import com.yinghangjiaclient.personal.BoughtActivity;
import com.yinghangjiaclient.util.HttpUtil;
import com.yinghangjiaclient.util.JSONUtils;
import com.yinghangjiaclient.util.StringUtils;
import com.yinghangjiaclient.util.UserButtonOnClickListener;
import com.yinghangjiaclient.util.UserUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsDetailActivity extends AppCompatActivity {
    private String newsId;
    private boolean flag = true;
    private CheckBox collectBtn;
    private boolean isTheFirstTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.init("ying");
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.zixun_second);

            new MyAsyncTask1().execute();

            WebView myweb = (WebView) findViewById(R.id.webview);
            Intent intent = this.getIntent();
            myweb.loadUrl(intent.getStringExtra("url"));
            myweb.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
            WebSettings settings = myweb.getSettings();
            settings.setJavaScriptEnabled(true);


            newsId = intent.getStringExtra("_id");
            collectBtn = (CheckBox) findViewById(R.id.button7);
            collectBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                    if (UserUtils.isLogin(NewsDetailActivity.this)) {
                        flag = arg1;
                        if (!isTheFirstTime) {
                            if (arg1) {
                                new MyAsyncTask().execute(0);
                            } else {
                                new MyAsyncTask().execute(1);
                            }
                        } else {
                            isTheFirstTime = false;
                        }
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(NewsDetailActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            });

            Button backBtn = (Button) findViewById(R.id.button3);
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }

    }

    
    
    
    

}
