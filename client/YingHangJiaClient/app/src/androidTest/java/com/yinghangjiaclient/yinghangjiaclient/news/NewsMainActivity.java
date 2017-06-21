package com.yinghangjiaclient.news;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.orhanobut.logger.Logger;
import com.yinghangjiaclient.R;
import com.yinghangjiaclient.util.HttpUtil;
import com.yinghangjiaclient.util.JSONUtils;
import com.yinghangjiaclient.util.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsMainActivity extends AppCompatActivity {
    /* 日期+(图片URL+标题+时间)*5 */
    private String[] keySet = {"ItemImage", "ItemTitle", "ItemTime"};
    private String[] MyURL;
    private String[] newsIds;
    private ListView newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.init("ying");
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.zixun_first);

            new MyAsyncTask().execute();
            newsList = (ListView) findViewById(R.id.listView3);
            newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println(MyURL[position] + "  " + position);
                    Intent intent = new Intent();
                    intent.putExtra("url", MyURL[position]);
                    intent.putExtra("_id", newsIds[position]);
                    intent.setClass(NewsMainActivity.this, NewsDetailActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
        }

    }

    
    
}

