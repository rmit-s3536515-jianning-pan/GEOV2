package com.example.pan.assignment1.controller;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pan.assignment1.adapter.TrackingListAdapter;
import com.example.pan.assignment1.database.TrackingSource;
import com.example.pan.assignment1.model.tracking.Tracking;

import java.util.List;

public class OnLongClickToRemoveItem implements View.OnLongClickListener {

//    private List<Tracking> data;
//    private Tracking item;
    private TrackingSource ts;
    private TrackingListAdapter adapter;
    private int id;
    public OnLongClickToRemoveItem(TrackingSource ts,int id,TrackingListAdapter adapter){ //List<Tracking> data ,Tracking item, RecyclerView.Adapter adapter){
//        this.data = data;
//        this.item =item;
        this.adapter = adapter;
        this.ts = ts;
        this.id = id;
    }
    @Override
    public boolean onLongClick(View v) {
        Integer result = ts.deleteTracking(String.valueOf(id));

        if(result > 0){
            System.out.println("Tracking is Deleted");
            Cursor c = ts.getAllTracking();
            adapter.swapCursor(c);


        }
        else{
            System.out.println("Tracking is NOT Deleted");
        }
        return true;
//        int pos  = data.indexOf(item);
//        data.remove(pos);
//        adapter.notifyItemRemoved(pos);
//        return true;
    }
}
