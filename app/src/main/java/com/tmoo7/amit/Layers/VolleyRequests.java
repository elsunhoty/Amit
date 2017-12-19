package com.tmoo7.amit.Layers;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by othello on 12/2/2017.
 */

public class VolleyRequests {

    private Context mContext;
    private  OnRequestFinished mOnRequestFinished;

    public VolleyRequests(Context context, final OnRequestFinished onRequestFinished ) {
        this.mContext = context;
        this.mOnRequestFinished = onRequestFinished;
    }
    public interface OnRequestFinished
    {
        void onrequestCompeleted(int Code, JSONObject userModelList);

    }

    public void volleyJsonObjectRequest(int Method,String url){

        final String  REQUEST_TAG = "Amit";
         JsonObjectRequest jsonObjectReq = new JsonObjectRequest(Method,url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(REQUEST_TAG, response.toString());
                      mOnRequestFinished.onrequestCompeleted(200, response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 NetworkResponse response = error.networkResponse;
                if (response != null)
                {
                    int code= response.statusCode;
                    Log.e(REQUEST_TAG, "Error: " + code);

                }
                else
                {
                    Log.e(REQUEST_TAG, "No Internet");

                }
                Log.e(REQUEST_TAG, "Error: " + error.getMessage());
                mOnRequestFinished.onrequestCompeleted(2, null);
             }
        });

         VolleySingleton.getInstance(mContext).addToRequestQueue(jsonObjectReq,REQUEST_TAG);
    }
 }
