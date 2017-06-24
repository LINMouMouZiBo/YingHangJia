package com.yinghangjiaclient;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.yinghangjiaclient.more.MoreMainActivity;
import com.yinghangjiaclient.news.NewsMainActivity;
import com.yinghangjiaclient.personal.PersonalMainActivity;
import com.yinghangjiaclient.recommend.RecommendMainActivity;
import com.yinghangjiaclient.recommend.UnLoginRecommendActivity;

public class MainActivity extends TabActivity {
    private static TabHost tabHost;

    private SharedPreferences sp;

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

    /**
     * �ж��Ƿ��¼��δ��¼����ת��¼����
     */
    private boolean loginJudge() {
       return sp.getBoolean("loginState", false);
    }
}
