package com.tmoo7.amit.Database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.tmoo7.amit.Models.UserModel;

/**
 * Created by othello on 12/19/2017.
 */

public class Dbhelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Amit.db";
    private static final int DB_VERSION = 1;
    private ContentResolver myCR;
    public Dbhelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        myCR = context.getContentResolver();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        addUSERINFOEntry(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    ////////   Add Function ///////////////////////////////////
    private void addUSERINFOEntry(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+MyDatabaseColums.USERINFOEntry.TABLE_NAME+" ("
                + MyDatabaseColums.USERINFOEntry.U_id+ " INTEGER , "
                + MyDatabaseColums.USERINFOEntry.langtitude+ " REAL, "
                + MyDatabaseColums.USERINFOEntry.latitude+ " REAL, "
                + MyDatabaseColums.USERINFOEntry.usernumber+ " TEXT, "
                + MyDatabaseColums.USERINFOEntry.address+ " TEXT, "
                + MyDatabaseColums.USERINFOEntry.userfk+ " INTEGER,"
                +"UNIQUE (" +MyDatabaseColums.USERINFOEntry.U_id+") ON CONFLICT IGNORE"
                +");");
    }
            //////////////////  Querys ////////////////////////////////////////

    public Uri adduser(UserModel  userModel) {

        ContentValues values = new ContentValues();
        values.put(MyDatabaseColums.USERINFOEntry.U_id, userModel.get_id());
        values.put(MyDatabaseColums.USERINFOEntry.langtitude, userModel.getLangtitude());
        values.put(MyDatabaseColums.USERINFOEntry.latitude, userModel.getLatitude());
        values.put(MyDatabaseColums.USERINFOEntry.usernumber, userModel.getUserNumber());
        values.put(MyDatabaseColums.USERINFOEntry.address, userModel.getAddress());
        values.put(MyDatabaseColums.USERINFOEntry.userfk, userModel.getUserFK());

        return myCR.insert(MyDatabaseColums.USERINFOEntry.CONTENT_URI, values);
    }
    public UserModel finduser(int _id) {
        String[] projection = {MyDatabaseColums.USERINFOEntry.U_id,
                MyDatabaseColums.USERINFOEntry.langtitude,MyDatabaseColums.USERINFOEntry.latitude
                ,MyDatabaseColums.USERINFOEntry.usernumber,MyDatabaseColums.USERINFOEntry.address,MyDatabaseColums.USERINFOEntry.userfk};

        String selection = "_id = \"" + _id + "\"";

        Cursor cursor = myCR.query(MyDatabaseColums.USERINFOEntry.CONTENT_URI,
                projection, selection, null,
                null);

        UserModel userModel= new UserModel();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            userModel.set_id(Integer.parseInt(cursor.getString(0)));
            userModel.setLangtitude(cursor.getDouble(1));
            userModel.setLatitude(cursor.getDouble(2));
            userModel.setUserNumber(cursor.getString(3));
            userModel.setAddress(cursor.getString(4));
            userModel.setUserFK(Integer.parseInt(cursor.getString(5)));
            cursor.close();
        } else {
            userModel = null;
        }
        return userModel;
    }
    public int LastSavedId()
    {
        String[] projection = {MyDatabaseColums.USERINFOEntry.U_id,
                MyDatabaseColums.USERINFOEntry.langtitude,MyDatabaseColums.USERINFOEntry.latitude
                ,MyDatabaseColums.USERINFOEntry.usernumber,MyDatabaseColums.USERINFOEntry.address,MyDatabaseColums.USERINFOEntry.userfk};


        Cursor cursor = myCR.query(MyDatabaseColums.USERINFOEntry.CONTENT_URI,
                projection, null, null,
                null);

        if (cursor.getCount() == 0)
        {
            return 0;
        }
        else
        {
            cursor.moveToLast();
          return  cursor.getInt(0);
        }
    }
}

