package com.yinghangjiaclient.util;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;



/**
 * Created by lin on 2017/6/24.
 */

public class UserUtils {
    static public boolean isLogin(Activity activity) {
        Context currentActivity = activity.getCurrentFocus().getContext();
        SharedPreferences sp = currentActivity.getSharedPreferences("userInfo",
                Activity.MODE_PRIVATE);
        return sp.getBoolean("loginState", false);

    }
}
