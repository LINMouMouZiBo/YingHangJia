package com.yinghangjiaclient.personal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yinghangjiaclient.R;

public class PersonalMainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.init("ying");
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mine_first);
            sp = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
            if (!sp.getBoolean("loginState", false)) {
                Intent intent = new Intent();
                intent.setClass(PersonalMainActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        TextView textView = (TextView) findViewById(R.id.textView8);
        textView.setText("用户名: " + sp.getString("USERNAME", ""));

        // 跳转到个人中心
        Button personal_center_btn = (Button) findViewById(R.id.personal_center_btn);
        personal_center_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(PersonalMainActivity.this,
                        PersonalCenterActivity.class);
                startActivity(intent1);
            }
        });

        Button personal_image_btn = (Button) findViewById(R.id.button6);
        personal_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(PersonalMainActivity.this,
                        PersonalCenterActivity.class);
                startActivity(intent1);
            }
        });

        Button personal_image_btn = (Button) findViewById(R.id.button6);
        personal_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(PersonalMainActivity.this,
                        PersonalCenterActivity.class);
                startActivity(intent1);
            }
        });

        TextView name = (TextView) findViewById(R.id.textView8);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setClass(PersonalMainActivity.this,
                        PersonalCenterActivity.class);
                startActivity(intent1);
            }
        });
            // 跳转到个人信息
            Button persenal_info_change_button = (Button) findViewById(R.id.persenal_info_change_button);
            persenal_info_change_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent();
                    intent1.setClass(PersonalCenterActivity.this,
                            PersonalInfoActivity.class);
                    startActivity(intent1);
                }
            });

            // 跳转到积分
            Button score_btn = (Button) findViewById(R.id.score_btn);
            score_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent();
                    intent1.setClass(PersonalMainActivity.this,
                            ScoreActivity.class);
                    startActivity(intent1);
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
