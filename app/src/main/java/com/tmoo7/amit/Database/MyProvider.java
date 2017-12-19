package com.tmoo7.amit.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import org.jetbrains.annotations.Nullable;

/**
 * Created by othello on 12/18/2017.
 */

public class MyProvider extends ContentProvider {

    // Use an int for each URI we will run, this represents the different queries
    //////
    private static final int USERINFO = 100;
    private static final int USERINFO_ID = 101;
    ////


    private Dbhelper mOpenHelper;
    // Use an int for each URI we will run, this represents the different queries
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        String content = MyDatabaseColums.CONTENT_AUTHORITY;
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        ///////////////////////////////////////////////////////////////////////////
        matcher.addURI(content, MyDatabaseColums.PATH_USERINFO, USERINFO);
        matcher.addURI(content, MyDatabaseColums.PATH_USERINFO + "/#", USERINFO_ID);
        ///////////////////////////////////////////////////////////////////////////
        return matcher;
    }
    @Override
    public boolean onCreate() {
        mOpenHelper = new Dbhelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Cursor retCursor;
        switch(sUriMatcher.match(uri)){
            case USERINFO:
                retCursor = db.query(
                        MyDatabaseColums.USERINFOEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case USERINFO_ID:
                long _id = ContentUris.parseId(uri);
                retCursor = db.query(
                        MyDatabaseColums.USERINFOEntry.TABLE_NAME,
                        projection,
                        MyDatabaseColums.USERINFOEntry._ID + " = ?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            /////////////////////////////////////////////////////////////////////

            /////////////////////////////////////////////////////////////////////////////////////////
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch(sUriMatcher.match(uri)){
            case USERINFO:
                return MyDatabaseColums.USERINFOEntry.CONTENT_TYPE;
            case USERINFO_ID:
                return MyDatabaseColums.USERINFOEntry.CONTENT_ITEM_TYPE;
            ////
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long _id;
        Uri returnUri;

        switch(sUriMatcher.match(uri)){
            case USERINFO:
                _id = db.insert(MyDatabaseColums.USERINFOEntry.TABLE_NAME, null, values);
                if(_id > 0){
                    returnUri =  MyDatabaseColums.USERINFOEntry.buildMovieUri(_id);
                } else{
                    throw new UnsupportedOperationException("Unable to insert rows into: " + uri);
                }
                break;
            ///////////
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Use this on the URI passed into the function to notify any observers that the uri has

        // changed.

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows; // Number of rows effected

        switch(sUriMatcher.match(uri)){
            case USERINFO:
                rows = db.delete(MyDatabaseColums.USERINFOEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Because null could delete all rows:

        if(selection == null || rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows;

        switch(sUriMatcher.match(uri)){
            case USERINFO:
                rows = db.update(MyDatabaseColums.USERINFOEntry.TABLE_NAME, values, selection, selectionArgs);
                break;



            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;    }
}
