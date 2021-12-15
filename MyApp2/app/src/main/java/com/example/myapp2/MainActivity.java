package com.example.myapp2;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Calling API");
        request();
        requestPermissions();
    }

    private void request(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.0.111:5000/ping";
        Map<String,String> map=new HashMap<>();
        map.put("pingString","Hitted!!!");

        JSONObject objects=new JSONObject(map);
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
                Log.d("Error",error.getLocalizedMessage());
            }
        });
        queue.add(objectRequest);
    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Log.d(TAG, "Permission Granted");
                    try {
                        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                        String pathDir = baseDir + "/Download";

                        File f = new File(pathDir + File.separator);

                        for(File file : f.listFiles()){
                            Log.d(TAG,file.getAbsolutePath());
                        }
                    }
                    catch (Exception e){
                        Log.d(TAG,"Cannot access files");
                    }
                } else {
                    Log.d(TAG,"Permission Declined");
                    try {
                        for(File file : Environment.getExternalStorageDirectory().listFiles()){
                            Log.d(TAG,file.getAbsolutePath());
                        }
                    }
                    catch (Exception e){
                        Log.d(TAG,"Cannot access files");
                    }
                }
            });

    private void requestPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"Granted");
            try {
                for(File file : Environment.getExternalStorageDirectory().listFiles()){
                    Log.d(TAG,file.getAbsolutePath());
                }
                String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                String pathDir = baseDir + "/Download";
                File f = new File(pathDir);

                Log.d(TAG, "requestPermissions: "+ f.listFiles().length);
                for(File file : f.listFiles()){
                    Log.d(TAG,file.getAbsolutePath());
                }

            }
            catch (Exception e){
                Log.d(TAG,"Error"+e);
            }
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Log.d(TAG,"Middle Granted");
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);

        } else {
            Log.d(TAG,"Not Granted");
            try {
                for(File file : Environment.getExternalStorageDirectory().listFiles()){
                    Log.d(TAG,file.getAbsolutePath());
                }
            }
            catch (Exception e){
                Log.d(TAG,"Cannot access files");
            }

            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

    }
}