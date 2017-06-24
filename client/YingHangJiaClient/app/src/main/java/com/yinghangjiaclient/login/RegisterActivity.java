package com.yinghangjiaclient.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.orhanobut.logger.Logger;
import com.yinghangjiaclient.R;
import com.yinghangjiaclient.util.HttpUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends Activity {
    private EditText usrEditTest, pwdEditTest, pwdAgainEditTest;
    private ProgressDialog progressDialog;
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
            progressDialog.setTitle("��ʾ��Ϣ");
            progressDialog.setMessage("���ڴ���...");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            //Ϊע�ᰴť�����¼�
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
                        //���ѡ�У���ʾ����
                        pwdEditTest.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }else{
                        //������������
                        pwdEditTest.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });

            CheckBox password_visible_button_confime = (CheckBox) findViewById(R.id.password_visible_button_confime);
            password_visible_button_confime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                    if(isChecked){
                        //���ѡ�У���ʾ����
                        pwdEditTest.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }else{
                        //������������
                        pwdEditTest.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });

            //Ϊȡ����ť�����¼�
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
     * ����һ���࣬����̳�AsyncTask����� Params: String���ͣ���ʾ���ݸ��첽����Ĳ���������String��ͨ��ָ������URL·��,������void
     * Progress: Integer���ͣ��������ĵ�λͨ������Integer���� Result��boolean���Ƿ��½�ɹ�
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
                showDialog("ע��ɹ���");
                // �ɹ����Զ���¼
//                SharedPreferences sp =
//                        getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
//                sp.edit().putBoolean("loginState", true);
                //                ����activity
                Intent intent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivities(new Intent[]{intent});
                RegisterActivity.this.finish();
            } else {
                showDialog("�û����Ѵ��ڣ������");
            }
        }
    }

    //���û���������зǿ���֤
    private boolean validate() {
        String usrname = usrEditTest.getText().toString();
        if (usrname.equals("")) {
            showDialog("�û���������");
            return false;
        }
        String pwd = pwdEditTest.getText().toString();
        if (pwd.equals("")) {
            showDialog("���������");
            return false;
        }
        String pwdAgain = pwdAgainEditTest.getText().toString();
        if (!pwd.equals(pwdAgain)) {
            showDialog("���벻ƥ��");
            return false;
        }
        return true;
    }

    //��ʾ��ʾ��Ϣ�ĶԻ���
    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //ͨ���û�����������в�ѯ������post���󣬵õ���Ӧ
    private String query(String username, String password) {

        String queryString = "username=" + username + "&password=" + password;
        String url = HttpUtil.BASE_URL + "LoginServlet?" + queryString;
        //return HttpUtil.queryStringForGet(url);

        url = HttpUtil.BASE_URL + "api/signup";
        NameValuePair paraUsername = new BasicNameValuePair("name",
                username);
        NameValuePair paraPassword = new BasicNameValuePair("password",
                password);
        List<NameValuePair> para = new ArrayList<NameValuePair>();
        para.add(paraPassword);
        para.add(paraUsername);
        return HttpUtil.queryStringForPost(url, para);
    }

    //����login ����
    private boolean login() {
        String usrname = usrEditTest.getText().toString();
        String pwd = pwdEditTest.getText().toString();
        String result = query(usrname, pwd);
        return result != null && result.equals("OK");
    }
}
