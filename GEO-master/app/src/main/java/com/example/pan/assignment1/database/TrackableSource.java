package com.example.pan.assignment1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.AsyncTask;


import com.example.pan.assignment1.model.trackable.Trackable;
import com.example.pan.assignment1.model.trackable.TrackableManager;

public class TrackableSource  extends AsyncTask<Void,Void,Void>{

    private Context context;
    private SQLiteDatabase mDatabase;
//    private TrackableListAdapter adapter;

    public TrackableSource(Context context){//,TrackableListAdapter adapter
        this.context = context;
        mDatabase = DBHelper.getSingletonInstance(context).getWritableDatabase();
//        this.adapter = adapter;
    }

    public void open(){
        mDatabase = DBHelper.getSingletonInstance(context).getWritableDatabase();
    }

    public void close(){
        DBHelper.getSingletonInstance(context).close();
    }

    public void addSomeTrackables(){
        mDatabase.beginTransaction();
        deleteAll();
        try {
            for(Trackable t : TrackableManager.getTrackables()){
                boolean isInserted =  insertTrackable(t);
                if(isInserted) System.out.println("Database , data " + t.getId() +"is inserted ");
                else System.out.println("Database is not inserted ");
            }
            mDatabase.setTransactionSuccessful();
        }catch (Exception e){

        }
        finally {
            mDatabase.endTransaction();
        }


    }


    public void deleteAll(){
        mDatabase.execSQL("delete from " + TrackableTable.TABLE_TRACKABLE);
    }

    public boolean insertTrackable(Trackable trackable){
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(TrackableTable.COLUMN_NAME,trackable.getName());
        contentvalues.put(TrackableTable.COLUMN_DESCRIPTION,trackable.getDescription());
        contentvalues.put(TrackableTable.COLUMN_URL,trackable.getUrl());
        contentvalues.put(TrackableTable.COLUMN_CATEGORY,trackable.getCategory());
        long result = mDatabase.insertOrThrow(TrackableTable.TABLE_TRACKABLE,null,contentvalues);
        if(result== -1)
            return false;
        else
            return true;
    }


    public Cursor getAllItem(String category){
        if(category==null || category.equals("All")){
            return mDatabase.query(TrackableTable.TABLE_TRACKABLE,null,null,null,null,null,null);
        }
       else{
            return mDatabase.query(TrackableTable.TABLE_TRACKABLE,null,TrackableTable.COLUMN_CATEGORY+"=?",new String[]{category},null,null,null);
        }
    }

//    public Trackable getTrackableById(String id){
//        String SQL_SEARCH_BY_ID = "Select * from " + TrackableTable.TABLE_TRACKABLE + " Where " + TrackableTable.COLUMN_ID +" = " + id +";";
//
//    }
//    public List<Trackable> getAllItems(){
//        List<Trackable> items = new ArrayList<>();
//        Cursor cursor = mDatabase.query(TrackableTable.TABLE_TRACKABLE,TrackableTable.ALL_COLUMN,null,null,null,null,TrackableTable.COLUMN_NAME);
//
//        while(cursor.moveToNext()){
//
////                String
////                String id = cursor.getString(cursor.getColumnIndex(TrackableTable.COLUMN_ID));
//            String name = cursor.getString(cursor.getColumnIndex(TrackableTable.COLUMN_NAME));
//            String description = cursor.getString(cursor.getColumnIndex(TrackableTable.COLUMN_DESCRIPTION));
//            String url  = cursor.getString(cursor.getColumnIndex(TrackableTable.COLUMN_URL));
//            String category = cursor.getString(cursor.getColumnIndex(TrackableTable.COLUMN_CATEGORY));
//            Trackable t = new FoodTruck(name,description,url,category);
//
//            System.out.println("Data Id "   + t.getId());
//            items.add(t);
//        }
//        cursor.close();
//        return items;
//    }

    @Override
    protected Void doInBackground(Void... voids) {
//        this.open();
        this.deleteAll();
        this.addSomeTrackables();
//        adapter.swapCursor(getAllItem(null));
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//        this.close();


    }
}
