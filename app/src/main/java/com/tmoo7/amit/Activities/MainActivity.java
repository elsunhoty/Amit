package com.tmoo7.amit.Activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.android.volley.Request;
import com.tmoo7.amit.Layers.SyncWithSqlite;
import com.tmoo7.amit.Layers.VolleyRequests;
import com.tmoo7.amit.R;

import org.json.JSONObject;

public class MainActivity extends Activity implements VolleyRequests.OnRequestFinished {

    VolleyRequests volleyRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volleyRequests = new VolleyRequests(getApplicationContext(),this);
        volleyRequests.volleyJsonObjectRequest(Request.Method.GET,buid_url());

    }

    @Override
    public void onrequestCompeleted(int Code, JSONObject userModelList) {
            if(Code==200)
            {
                SyncWithSqlite syncWithSqlite = new SyncWithSqlite(MainActivity.this,userModelList);
            }
    }
    private String buid_url()
    {
         Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("amit-learning.com")
                .appendPath("parkForMe")
                .appendPath("index.php");
        return builder.build().toString();
    }
}
