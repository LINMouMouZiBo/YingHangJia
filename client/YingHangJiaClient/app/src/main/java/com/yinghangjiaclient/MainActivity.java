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

        //        下面几行酌情增加或修改，修改就改xxxxActivity为所需页面
        intent = new Intent().setClass(this, UnLoginRecommendActivity.class);
        spec = tabHost.newTabSpec("推荐unlogin").setIndicator("推荐").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, RecommendMainActivity.class);
        spec = tabHost.newTabSpec("推荐login").setIndicator("推荐").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, NewsMainActivity.class);
        spec = tabHost.newTabSpec("资讯").setIndicator("推荐").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, PersonalMainActivity.class);
        spec = tabHost.newTabSpec("我的").setIndicator("推荐").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, MoreMainActivity.class);
        spec = tabHost.newTabSpec("更多").setIndicator("推荐").setContent(intent);
        tabHost.addTab(spec);
        if (loginJudge()) {
            tabHost.setCurrentTabByTag("推荐login");
        } else {
            tabHost.setCurrentTabByTag("推荐unlogin");
        }

        //        这个ID是radioGroup的ID，对于不同的group设置不同值，否则会崩溃
        RadioGroup radioGroup = (RadioGroup) this
                .findViewById(R.id.main_tab_group);
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId) {
                        switch (checkedId) {
                            case R.id.main_tab_recomment:// 资讯
                                if (loginJudge()) {
                                    tabHost.setCurrentTabByTag("推荐login");
                                } else {
                                    tabHost.setCurrentTabByTag("推荐unlogin");
                                }
                                break;
                            case R.id.main_tab_zixun:// 资讯
                                tabHost.setCurrentTabByTag("资讯");
                                break;
                            case R.id.main_tab_me:// 我的
//                                loginJudge();
                                tabHost.setCurrentTabByTag("我的");
                                break;
                            case R.id.main_tab_more:// 更多
//                                loginJudge();
                                tabHost.setCurrentTabByTag("更多");
                                break;
                            default:
                                break;
                        }
                    }
                });
    }
}
