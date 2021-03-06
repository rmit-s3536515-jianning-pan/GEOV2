package com.example.pan.assignment1.view.fragments;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
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
import com.example.pan.assignment1.service.MyJobService;
import com.example.pan.assignment1.service.TrackingService;

import java.util.concurrent.TimeUnit;

public class TrackingList extends AppCompatActivity {

    private TrackingSource ts;
    private static TrackingListAdapter adapter;
    private static Context context;

    public static final int JOB_ID = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_list);
        context = this;
        ts = new TrackingSource(this);
        ts.execute();

        init();

        scheduleJob();
    }

    public static Context getContext(){
        return context;
    }

    public static TrackingListAdapter getAdapter(){
        return adapter;
    }

    public void scheduleJob(){
        JobScheduler jobscheduler =(JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo jobinfo = new JobInfo.Builder(JOB_ID, new ComponentName(this, MyJobService.class))
                .setMinimumLatency(0)
//                .setPeriodic(TimeUnit.MINUTES.toMillis(5))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();
        jobscheduler.schedule(jobinfo);
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
