package com.tmoo7.amit.Layers;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.tmoo7.amit.Database.Dbhelper;
import com.tmoo7.amit.Models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by othello on 12/19/2017.
 */

public class SyncWithSqlite {
    Context mContext;
    JSONObject mobject;
    Dbhelper mDbhelper;
    List<UserModel> mUserModels;
    public SyncWithSqlite(Context context , JSONObject jsonObject) {
        this.mContext = context;
        this.mobject = jsonObject;
        mDbhelper = new Dbhelper(context);
        mUserModels = new ArrayList<>();
        AddDataToList();
  //      Check_last_id();
        AddToSqlite();

    }
    private void Check_last_id()
    {

     int lastsavedid =   mDbhelper.LastSavedId();
        Log.e("lastsavedid",lastsavedid+"//");
    }
    private void AddDataToList()
    {
        try {
            JSONArray array = mobject.getJSONArray("data");
            for (int i = 0 ; i<array.length();i++)
            {
                double latitude = 0;
                double langtitude = 0;
                JSONObject object = array.getJSONObject(i);
                int _id =  object.getInt("id");
                int fk = object.getInt("userFK");
                String address = object.getString("address");
                String userNumber = object.getString("userNumber");

                String elat = object.getString("latitude");
                String  elng = object.getString("langtitude");
                try
                {
                    latitude = Double.parseDouble(elat);
                    langtitude = Double.parseDouble(elng);
                }
                catch (NumberFormatException  e)
                {
                    //
                }
                UserModel userModel = new UserModel(_id,fk,address,userNumber,latitude,langtitude);
                Log.e("alskdj",latitude+"V");
                mUserModels.add(userModel);


            }
        } catch (JSONException e) {
            Log.e("Errror",e.getMessage());
        }
    }
    private void AddToSqlite()
    {
        for (UserModel userModel:mUserModels)
        {
         Uri res =  mDbhelper.adduser(userModel);
            Log.e("result",res.getPath());
        }
    }
}
