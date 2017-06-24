package com.yinghangjiaclient;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }
    String MY_ACTION = "OnItemLongClick";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MY_ACTION)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                SerializableMap serializableMap = (SerializableMap) bundle.get("map");
                Map<String, Object> map = serializableMap.getMap();
                byte[] bis = (byte[]) map.get("image");
                Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
                String content = map.get("content").toString();

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(context);
                builder.setContentTitle("赢行家")
                        .setContentText(content)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(bitmap)
                        .setAutoCancel(true)
                        .setTicker(content);
                Intent mIntent = new Intent(context, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
                builder.setContentIntent(pendingIntent);
                Notification notification = builder.build();
                notificationManager.notify(0, notification);
            }
        }
    }
}
