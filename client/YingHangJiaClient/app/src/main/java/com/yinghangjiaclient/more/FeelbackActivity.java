package com.yinghangjiaclient.more;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.yinghangjiaclient.R;
import com.yinghangjiaclient.util.HttpUtil;
import com.yinghangjiaclient.util.StringUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class FeelbackActivity extends AppCompatActivity {
    private EditText feelbackContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.init("ying");
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.feedback_center);
            feelbackContext = (EditText) findViewById(R.id.editText2);

            // 登录按钮监听
            Button feedback_push_button = (Button) findViewById(R.id.feedback_push_button);
            feedback_push_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (!StringUtils.isBlank(feelbackContext.getText().toString()))
                        new MyAsyncTask().execute();
                    else
                        Toast.makeText(getApplicationContext(), "输入不能为空", Toast.LENGTH_SHORT).show();;
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
