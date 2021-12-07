package com.example.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private static final String PRIMARY_CHANNEL_ID = "primary notification channel";
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager notificationManager;

    Button btnNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        btnNotify=findViewById(R.id.notify);
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
                notificationManager.notify(NOTIFICATION_ID,notifyBuilder.build());
            }
        });

    }
    public void createNotificationChannel(){
        notificationManager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,"Mascot Notification",
                                                                                NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(false);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(false);
            notificationChannel.setDescription("Notification from Mascot");
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    private NotificationCompat.Builder getNotificationBuilder(){

        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.music_notification_default);
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.music_notification_bigstyle);

        /* Bằng cách sử dụng PendingIntent để giao tiếp với một ứng dụng khác,
        bạn đang yêu cầu ứng dụng đó thực thi một số mã xác định trước vào một thời điểm nào đó trong tương lai.
        Nó giống như ứng dụng khác có thể thực hiện một hành động thay mặt cho ứng dụng của bạn. */
        NotificationCompat.Builder customNotification = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID);
        customNotification.setSmallIcon(R.drawable.ic_android);
        customNotification     .setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        customNotification   .setCustomContentView(notificationLayout);
        customNotification    .setCustomBigContentView(notificationLayoutExpanded);
        customNotification    .build();
        return customNotification;
    };
}