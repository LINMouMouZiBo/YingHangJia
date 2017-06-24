package com.yinghangjiaclient;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.yinghangjiaclient.recommend.ProduceMainActivity;

import java.util.Map;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    String MY_ACTION = "OnItemLongClick";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Intent clickIntent = new Intent(context, MainActivity.class);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clickIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_image, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        Bundle bundle = intent.getExtras();
        if (intent.getAction().equals(MY_ACTION)) {
            if (bundle != null) {
                SerializableMap serializableMap = (SerializableMap) bundle.get("map");
                Map<String, Object> map = serializableMap.getMap();
                byte[] bis = (byte[]) map.get("image");
                Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
                String title = map.get("title").toString();
                String content = map.get("content").toString();
                remoteViews.setImageViewBitmap(R.id.widget_image, bitmap);
                remoteViews.setTextViewText(R.id.appwidget_text, "年化收益率：" + title);
                remoteViews.setTextViewText(R.id.appwidget_text1, content);
                String _id = map.get("_id").toString();

                Intent clickIntent = new Intent(context, ProduceMainActivity.class);
                clickIntent.putExtra("_id", _id);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clickIntent, 0);
                remoteViews.setOnClickPendingIntent(R.id.widget_image, pendingIntent);

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                appWidgetManager.updateAppWidget(new ComponentName(context, NewAppWidget.class), remoteViews);
            }
        }
    }
}

