package ru.gdgkazan.firebasechat.service;

import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import ru.gdgkazan.firebasechat.R;
import ru.gdgkazan.firebasechat.activity.AuthActivity;

/**
 * @author Artur Vasilov
 */
public class ChatFCMService extends FirebaseMessagingService {

    private static final String TITLE = "title";
    private static final String BODY = "body";

    private NotificationManagerCompat mNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = NotificationManagerCompat.from(this);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData() != null) {
            Map<String, String> pushParams = remoteMessage.getData();
            showNotification(pushParams);
        }
    }

    private void showNotification(@NonNull Map<String, String> remoteMessage) {
        Intent intent = new Intent(this, AuthActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);
        Uri notificationRingtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        String title = remoteMessage.get(TITLE);
        String body = remoteMessage.get(BODY);

        NotificationCompat.BigTextStyle notificationStyle = new NotificationCompat.BigTextStyle()
                .setBigContentTitle(title)
                .bigText(body);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(notificationRingtone)
                .setStyle(notificationStyle)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher);
        mNotificationManager.notify(0, builder.build());
    }

}
