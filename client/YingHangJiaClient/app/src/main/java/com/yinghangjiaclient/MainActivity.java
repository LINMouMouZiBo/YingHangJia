package com.yinghangjiaclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yinghangjiaclient.yinghangjiaclient.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);

        tabHost = this.getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        //        ���漸���������ӻ��޸ģ��޸ľ͸�xxxxActivityΪ����ҳ��
        intent = new Intent().setClass(this, UnLoginRecommendActivity.class);
        spec = tabHost.newTabSpec("�Ƽ�unlogin").setIndicator("�Ƽ�").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, RecommendMainActivity.class);
        spec = tabHost.newTabSpec("�Ƽ�login").setIndicator("�Ƽ�").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, NewsMainActivity.class);
        spec = tabHost.newTabSpec("��Ѷ").setIndicator("�Ƽ�").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, PersonalMainActivity.class);
        spec = tabHost.newTabSpec("�ҵ�").setIndicator("�Ƽ�").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, MoreMainActivity.class);
        spec = tabHost.newTabSpec("����").setIndicator("�Ƽ�").setContent(intent);
        tabHost.addTab(spec);
        if (loginJudge()) {
            tabHost.setCurrentTabByTag("�Ƽ�login");
        } else {
            tabHost.setCurrentTabByTag("�Ƽ�unlogin");
        }

        //        ���ID��radioGroup��ID�����ڲ�ͬ��group���ò�ֵͬ����������
        RadioGroup radioGroup = (RadioGroup) this
                .findViewById(R.id.main_tab_group);
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId) {
                        switch (checkedId) {
                            case R.id.main_tab_recomment:// ��Ѷ
                                if (loginJudge()) {
                                    tabHost.setCurrentTabByTag("�Ƽ�login");
                                } else {
                                    tabHost.setCurrentTabByTag("�Ƽ�unlogin");
                                }
                                break;
                            case R.id.main_tab_zixun:// ��Ѷ
                                tabHost.setCurrentTabByTag("��Ѷ");
                                break;
                            case R.id.main_tab_me:// �ҵ�
//                                loginJudge();
                                tabHost.setCurrentTabByTag("�ҵ�");
                                break;
                            case R.id.main_tab_more:// ����
//                                loginJudge();
                                tabHost.setCurrentTabByTag("����");
                                break;
                            default:
                                break;
                        }
                    }
                });
    }
}
