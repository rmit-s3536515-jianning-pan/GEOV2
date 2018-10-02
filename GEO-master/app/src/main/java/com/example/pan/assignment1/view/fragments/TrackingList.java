package com.example.pan.assignment1.view.fragments;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pan.assignment1.MainActivity;
import com.example.pan.assignment1.R;
import com.example.pan.assignment1.adapter.TrackableListAdapter;
import com.example.pan.assignment1.adapter.TrackingListAdapter;
import com.example.pan.assignment1.database.TrackingSource;
import com.example.pan.assignment1.model.trackable.TrackableManager;
import com.example.pan.assignment1.model.tracking.TrackingManager;
import com.example.pan.assignment1.service.TrackingService;

public class TrackingList extends AppCompatActivity {

    private TrackingSource ts;
    private static TrackingListAdapter adapter;
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_list);
        context = this;
        ts = new TrackingSource(this);
        ts.execute();

        init();
    }

    public static Context getContext(){
        return context;
    }

    public static TrackingListAdapter getAdapter(){
        return adapter;
    }
    private void init(){
        RecyclerView rv;
        RecyclerView.LayoutManager layoutmanager;



        rv = findViewById(R.id.recycleView);
        rv.setHasFixedSize(true);

        layoutmanager = new LinearLayoutManager(this);
        layoutmanager.setMeasurementCacheEnabled(false);
        rv.setLayoutManager(layoutmanager);

        Cursor c = ts.getAllTracking();
        adapter = new TrackingListAdapter(this, c,ts); // TrackingManager.getTrackingList()
//        adapter.swapCursor(c);
        rv.setAdapter(adapter);
    }


}
