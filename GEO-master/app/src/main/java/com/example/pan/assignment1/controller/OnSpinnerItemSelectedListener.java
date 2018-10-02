package com.example.pan.assignment1.controller;

import android.content.Context;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.pan.assignment1.adapter.TrackableListAdapter;
import com.example.pan.assignment1.database.DBHelper;
import com.example.pan.assignment1.database.TrackableSource;
import com.example.pan.assignment1.model.trackable.TrackableManager;

public class OnSpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener{
    private Context context;
    private  TrackableListAdapter adapter;
    private TrackableSource ts;
    public  OnSpinnerItemSelectedListener(Context context, TrackableListAdapter adapter,TrackableSource ts){
        this.context = context;
        this.adapter = adapter;
        this.ts = ts;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Cursor c = ts.getAllItem(adapterView.getItemAtPosition(i).toString());
        if(c!=null){
            System.out.println("Cursor is " + c.getCount());
            adapter.swapCursor(c);
        }
        else{
            System.out.println("Cursor is null");
        }

//        TrackableListAdapter.updateDataset(TrackableManager.findTrackableByCategory(adapterView.getItemAtPosition(i).toString()));
//        adapter.notifyDataSetChanged();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
