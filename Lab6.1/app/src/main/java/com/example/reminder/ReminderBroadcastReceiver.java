package com.example.reminder;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

// BroadcastReceiver is a base class for code that receives and handles broadcast intents by Context.sendBroadcast(Intent)
public class ReminderBroadcastReceiver extends BroadcastReceiver {

    int notificationId = 100;

    @Override
    public void onReceive(Context context, Intent intent) {

        // Create intent that is sent when user taps the notification
        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notificationId, mainIntent, 0);

        // Notification content and channel set
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ReminderApplication.CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Your Reminder!")
                .setContentText(intent.getStringExtra("notificationText"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) // On Android 7.1 and lower determines how intrusive the notification is
                .setContentIntent(pendingIntent) // When notification is tapped, call MainActivity.
                .setAutoCancel(true); // Notification is automatically removed when tapped

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder.build());
    }
}