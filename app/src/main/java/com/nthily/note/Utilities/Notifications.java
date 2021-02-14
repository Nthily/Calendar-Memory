package com.nthily.note.Utilities;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import static android.content.Context.NOTIFICATION_SERVICE;

import com.nthily.note.R;

public class Notifications {

    public static NotificationManager manager;

    public static void initNotification(Context context, CharSequence cs) {
        setNotificationChannel(context);
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context, "chat")
                .setAutoCancel(false)
                .setContentTitle("日程本")
                .setContentText(cs)
                .setSmallIcon(R.mipmap.ic_app)
                .setShowWhen(false)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_app))
                .build();

        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        manager.notify(1, notification);
    }

    public static void setNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "每日一句";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(context, channelId, channelName, importance);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private static void createNotificationChannel(Context context, String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

}
