package com.example.pan.assignment1.database;

public class TrackableTable {
    public static final String TABLE_TRACKABLE = "Trackables";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_CATEGORY = "category";

    public static final String[] ALL_COLUMN = {COLUMN_ID,COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_URL,COLUMN_CATEGORY};
    public static final String SQL_CREATE = "CREATE TABLE "+ TABLE_TRACKABLE + "("+
            COLUMN_ID + " INTEGER PRIMARY KEY ," + //AUTOINCREMENT
            COLUMN_NAME + " TEXT," +
            COLUMN_DESCRIPTION + " TEXT," +
            COLUMN_URL + " TEXT," +
            COLUMN_CATEGORY + " TEXT" + ");";

    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS " + TABLE_TRACKABLE;


    public static final  String SQL_ALLDATA = "Select * from " + TABLE_TRACKABLE;

}
