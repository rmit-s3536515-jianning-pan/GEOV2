package com.example.pan.assignment1.database;

public class TrackingTable {
    public static final String TABLE_TRACKING = "Tracking";
    public static final String COLUMN_ID = "Tracking_Id";
    public static final String COLUMN_TRACK_ID = "trackable_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TARGET_START_TIME= "targetStartTime";
    public static final String COLUMN_TARGET_END_TIME = "targetEndTime";
    public static final String COLUMN_MEET_TIME = "meetTime";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_STOP_TIME = "stoptime";

    public static final String SQL_CREATE = "CREATE TABLE "+ TABLE_TRACKING + "("+
            "id" + " INTEGER PRIMARY KEY AUTOINCREMENT ," + //AUTOINCREMENT
            COLUMN_ID + " Text," +
            COLUMN_TRACK_ID + " INTEGER NOT NULL," +
            COLUMN_TITLE + " TEXT," +
            COLUMN_TARGET_START_TIME + " DATETIME," +
            COLUMN_TARGET_END_TIME + " DATETIME," +
            COLUMN_MEET_TIME + " DATETIME," +
            COLUMN_LATITUDE + " REAL," +
            COLUMN_LONGITUDE + " REAL," +
            COLUMN_STOP_TIME + " INTEGER" +");";

//            COLUMN_TRACKABLE_ID + " INTEGER NOT NULL, FOREIGN KEY (" +
//            COLUMN_TRACKABLE_ID + ") REFERENCES " + TrackableTable.TABLE_TRACKABLE + "(" + TrackableTable.COLUMN_ID+") ON DELETE CASCADE);";


    public static final String SQL_DROP =
            "DROP TABLE IF EXISTS " + TABLE_TRACKING;
}
