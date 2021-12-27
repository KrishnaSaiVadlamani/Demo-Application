package com.example.myandroidapps.provider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.example.myandroidapps.R;
import com.example.myandroidapps.util.RequestHandler;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationProvider {

    private final String TAG;
    private final Context context;
    private final FusedLocationProviderClient fusedLocationProviderClient;
    private final LocationManager locationManager;


    public LocationProvider(Context context){
        this.context = context;
        this.TAG = context.getResources().getString(R.string.app_name);
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void handleReportLocation() {
        Log.d(TAG, "locationDimensions: "+locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
        Log.d(TAG, "locationDimensions: "+locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                return;
            }
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                Location location = task.getResult();
                if (location != null) {
                    JSONObject object=new JSONObject();
                    Log.d(TAG, "onComplete: Latitude" + location.getLatitude());
                    Log.d(TAG, "onComplete: Longitude" + location.getLongitude());
                    try {
                        object.put("Latitude", location.getLatitude());
                        object.put("Longitude", location.getLongitude());
                    } catch (JSONException e) {
                        Log.e(TAG,"Failed to report Location",e);
                    }
                    Log.d(TAG, "onComplete: " + object);
                    RequestHandler.handleRequest(context,"location",object);
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
                                RequestHandler.handleRequest(context, "location",object);
                            } catch (JSONException e) {
                                Log.e(TAG,"Failed to handle report location",e);
                            }
                        }
                    };
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                            locationCallback, Looper.myLooper());
                }
            });
        }else{
            context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}
