package com.buscame.oncor.buscame.Firebase;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.buscame.oncor.buscame.Intro;
import com.buscame.oncor.buscame.R;
import com.buscame.oncor.buscame.Util.Util;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.buscame.oncor.buscame.DashBoard.Activity.DashBoard;

import java.util.Map;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;
import static com.google.android.gms.plus.PlusOneDummyView.TAG;

public class Notification extends FirebaseMessagingService {



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

       // Map<String, String> remoteData = remoteMessage.getData();
       // String notification = remoteMessage.getFrom();



        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {

            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

            if (Util.Companion.isAppRunning(this, "com.buscame.oncor.buscame")) {
                // App is running
               // Toast.makeText(this,"esta corriendo la app",Toast.LENGTH_SHORT).show();
                Log.e("mensaje","la app esta abierta");
                // Check if message contains a data payload.
                if (remoteMessage.getData().size() > 0) {
                    Log.d(TAG, "Message data payload: " + remoteMessage.getData());


                    LatLng latLng = new LatLng(Double.valueOf(remoteMessage.getData().get("lat")),Double.valueOf(remoteMessage.getData().get("lng")));
                    DashBoard.printMarketPosition(latLng, "gps traker",this, DashBoard.imgMarket);


                    DashBoard.showAndHideButtonBuscame(Boolean.valueOf(remoteMessage.getData().get("finish")));
                    // DashBoard.Companion.printMarketCommand(latLng);
                }


            } else {
                // App is not running
                showNotification(
                        title,
                        body
                );
            }


        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.


    }



    private void showNotification(String title,String body) {

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, Intro.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "0001")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


       // Issue the new notification.
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0001, mBuilder.build());
    }
}