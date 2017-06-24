package com.yinghangjiaclient.util;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import com.yinghangjiaclient.login.LoginActivity;


public class UserButtonOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Context currentActivity = v.getContext();
        SharedPreferences sp = currentActivity.getSharedPreferences("userInfo",
                Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        if (sp.getBoolean("loginState", false)) {
            return;
        }
        Intent i = new Intent(currentActivity, LoginActivity.class);
        // 启动

        currentActivity.startActivity(i);
    }
}

