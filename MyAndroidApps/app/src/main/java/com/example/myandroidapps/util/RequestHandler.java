package com.example.myandroidapps.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RequestHandler {

    private static final String SERVER = "http://34.69.31.130:1324/";
    private static final String TAG = "SyncApp";

    public static void handleRequest(Context context, String endpoint, JSONObject body){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = SERVER+endpoint;
        Response.Listener<JSONObject> listener = getResponseListener();
        Response.ErrorListener errorListener = getErrorListener();
        JsonObjectRequest objectRequest = new ApiRequest(Request.Method.POST,
                url,
                body,
                listener,
                errorListener);
        queue.add(objectRequest);
    }

    private static Response.Listener<JSONObject> getResponseListener(){
        return response -> Log.d(TAG, "Received Response. Size : " + response.length());
    }

    private static Response.ErrorListener getErrorListener(){
        return error -> Log.e(TAG,"Obtained Response Error",error);
    }
}
