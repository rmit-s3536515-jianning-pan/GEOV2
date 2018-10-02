package com.example.pan.assignment1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = DBHelper.class.getName();
    public static final String DB_FILE_NAME = "database.db";
    public static final int DB_VERSION = 2;

    private static DBHelper singletonInstance;

    public static DBHelper getSingletonInstance(Context context)
    {
        if (singletonInstance == null)
            singletonInstance = new DBHelper(
                    context.getApplicationContext());

        return singletonInstance;
    }
    private DBHelper(Context context)
    {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }
//    public DBHelper(Context context) {
//        super(context, DB_FILE_NAME, null, DB_VERSION);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Log some information about our database

        // CREATE TABLES
        Log.i(LOG_TAG, "Create the tbl_authors table using execSQL()");
        db.execSQL(TrackableTable.SQL_CREATE);
        db.execSQL(TrackingTable.SQL_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Version : "+ newVersion);
        db.execSQL(TrackableTable.SQL_DROP);
        db.execSQL(TrackingTable.SQL_DROP);
        onCreate(db);
    }

//    public void delete(SQLiteDatabase db){
//        db.execSQL("delete from " + TrackableTable.TABLE_TRACKABLE);
//
//    }
}
