package com.example.myandroidapps.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.util.Arrays;

public class PermissionHandler {

    private static final String TAG = "SyncApp";

    private static final String[] PERMISSIONS = new String[]
            {
                    Manifest.permission.READ_SMS,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };

    public static final int PERMISSION_REQUEST_CODE = 1;

    public static boolean checkPermissions(final Context context) {
        if(context != null){
            for(String permission: PERMISSIONS){
                if(ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    public static void requestPermissions(final Activity activity){
        ActivityCompat.requestPermissions(activity,PERMISSIONS,PERMISSION_REQUEST_CODE);
    }

    public static boolean isSmsPermissionGranted(final int[] grantedPermission){
        return isPermissionGranted(grantedPermission,Manifest.permission.READ_SMS);
    }

    public static boolean isLocationPermissionGranted(final int[] grantedPermission){
        return isPermissionGranted(grantedPermission,Manifest.permission.ACCESS_FINE_LOCATION);
    }


    private static boolean isPermissionGranted(final int[] grantedPermission, String permission){
        try{
            int permissionIndex = getPermissionIndex(permission);
            return grantedPermission[permissionIndex] == PackageManager.PERMISSION_GRANTED;
        }catch (Exception e){
            Log.e(TAG,"Failed to check isPermissionGranted",e);
        }
        return false;
    }

    private static int getPermissionIndex(String permission){
        return Arrays.asList(PERMISSIONS).indexOf(permission);
    }
}
