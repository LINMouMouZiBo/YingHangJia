package com.yinghangjiaclient.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yinghangjiaclient.yinghangjiaclient.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.init("ying");
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register_first);

            Button regBtn = (Button) findViewById(R.id.button2);
            Button cancelBtn = (Button) findViewById(R.id.button8);
            usrEditTest = (EditText) findViewById(R.id.userName);
            pwdEditTest = (EditText) findViewById(R.id.user_Password);
            pwdAgainEditTest =
                    (EditText) findViewById(R.id.user_Password_confime);

            progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setTitle("提示信息");
            progressDialog.setMessage("正在处理...");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            //为注册按钮添加事件
            regBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (validate()) {
                        new MyAsyncTask().execute();
                    }
                }
            });

            CheckBox user_name_delete_allinput = (CheckBox) findViewById(R.id.user_name_delete_allinput);
            user_name_delete_allinput.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                    usrEditTest.setText("");
                }
            });

            CheckBox password_delete_all = (CheckBox) findViewById(R.id.password_delete_all);
            password_delete_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                    pwdEditTest.setText("");
                }
            });

            CheckBox confime_password_delete_all = (CheckBox) findViewById(R.id.confime_password_delete_all);
            confime_password_delete_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                    pwdAgainEditTest.setText("");
                }
            });

            CheckBox password_visible_button = (CheckBox) findViewById(R.id.password_visible_button);
            password_visible_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                    if(isChecked){
                        //如果选中，显示密码
                        pwdEditTest.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }else{
                        //否则隐藏密码
                        pwdEditTest.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });

            CheckBox password_visible_button_confime = (CheckBox) findViewById(R.id.password_visible_button_confime);
            password_visible_button_confime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                    if(isChecked){
                        //如果选中，显示密码
                        pwdEditTest.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }else{
                        //否则隐藏密码
                        pwdEditTest.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });

            //为取消按钮添加事件
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }
    } /**
     * 定义一个类，让其继承AsyncTask这个类 Params: String类型，表示传递给异步任务的参数类型是String，通常指定的是URL路径,这里用void
     * Progress: Integer类型，进度条的单位通常都是Integer类型 Result：boolean，是否登陆成功
     */
    public class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            return login();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            if (result) {
                showDialog("注册成功！");
                // 成功后自动登录
//                SharedPreferences sp =
//                        getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
//                sp.edit().putBoolean("loginState", true);
                //                启动activity
                Intent intent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivities(new Intent[]{intent});
                RegisterActivity.this.finish();
            } else {
                showDialog("用户名已存在，请更换");
            }
        }
    }
}
