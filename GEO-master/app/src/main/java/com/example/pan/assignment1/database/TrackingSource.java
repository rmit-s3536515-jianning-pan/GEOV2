package com.example.pan.assignment1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.pan.assignment1.model.tracking.Tracking;
import com.example.pan.assignment1.model.tracking.TrackingManager;

public class TrackingSource extends AsyncTask<Void,Void,Void> {

    private Context context;
    private SQLiteDatabase mDatabase;

    public TrackingSource(Context context){
        this.context = context;
        mDatabase = DBHelper.getSingletonInstance(context).getWritableDatabase();
    }

    public Integer deleteTracking(String id){
        return mDatabase.delete(TrackingTable.TABLE_TRACKING, "id = ?",new String[]{id});
    }

    public boolean updateTracking(String id,String title,String startTime,String endTime ,String meetTime){
        ContentValues data = new ContentValues();
        data.put(TrackingTable.COLUMN_TITLE,title);
        data.put(TrackingTable.COLUMN_TARGET_START_TIME,startTime);
        data.put(TrackingTable.COLUMN_TARGET_END_TIME,endTime);
        data.put(TrackingTable.COLUMN_MEET_TIME,meetTime);
        mDatabase.update(TrackingTable.TABLE_TRACKING,data,"id"+ "= ? ",new String[]{id});
//        Cursor c = this.getAllTracking();

        return  true;
    }

    public void close(){
        DBHelper.getSingletonInstance(context).close();
    }

    public void deleteAll(){
        mDatabase.execSQL("delete from " + TrackingTable.TABLE_TRACKING);
    }

    public boolean insertTracking(Tracking tracking){
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(TrackingTable.COLUMN_TITLE,tracking.getTitle());
        contentvalues.put(TrackingTable.COLUMN_TARGET_START_TIME, TrackingManager.dateformat.format(tracking.getTargetStartTime()));
        contentvalues.put(TrackingTable.COLUMN_TARGET_END_TIME,TrackingManager.dateformat.format(tracking.getTargetEndTime()));
        contentvalues.put(TrackingTable.COLUMN_MEET_TIME,TrackingManager.dateformat.format(tracking.getMeetTime()));
        contentvalues.put(TrackingTable.COLUMN_TRACK_ID,tracking.getTrackableId());
        long result = mDatabase.insertOrThrow(TrackingTable.TABLE_TRACKING,null,contentvalues);
        if(result== -1)
            return false;
        else
            return true;
    }

    public Cursor getAllTracking(){
        return mDatabase.query(TrackingTable.TABLE_TRACKING,null,null,null,null,null,null);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        return null;
    }

}
