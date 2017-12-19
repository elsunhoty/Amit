package com.tmoo7.amit.Database;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by othello on 12/18/2017.
 */

public class MyDatabaseColums  {
    public static final String CONTENT_AUTHORITY = "com.tmoo7.amitdatabase";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //////////////////////////  PATH ////////////////////////
    public static final String PATH_USERINFO = "USERINFO";
    //////////////////////////  PATH ////////////////////////

    public static final class USERINFOEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USERINFO).build();
        public static final String CONTENT_TYPE ="vnd.android.cursor.dir/" + CONTENT_URI  + "/" + PATH_USERINFO;
        public static final String CONTENT_ITEM_TYPE ="vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_USERINFO;
        // Define the table schema

        public static final String TABLE_NAME = "USERINFO";

        public static final String U_id = "_id";
        public static final String langtitude = "langtitude";
        public static final String latitude = "latitude";
        public static final String usernumber = "userNumber";
        public static final String address = "address";
        public static final String userfk = "userfk";

        //
        public static Uri buildMovieUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }




}
