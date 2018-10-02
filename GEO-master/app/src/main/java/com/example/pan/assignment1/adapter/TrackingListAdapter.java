package com.example.pan.assignment1.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pan.assignment1.R;
import com.example.pan.assignment1.controller.OnLongClickToRemoveItem;
import com.example.pan.assignment1.controller.onTrackingItemClickedListener;
import com.example.pan.assignment1.database.TrackingSource;
import com.example.pan.assignment1.database.TrackingTable;
import com.example.pan.assignment1.model.trackable.TrackableManager;
import com.example.pan.assignment1.model.tracking.Tracking;
import com.example.pan.assignment1.model.tracking.TrackingManager;
import com.example.pan.assignment1.view.fragments.EditTrackingActivity;

import java.util.List;

public class TrackingListAdapter extends RecyclerView.Adapter<TrackingListAdapter.ViewHolder> {

    private Context context;
//    private List<Tracking> trackingDataset;
    private TrackingSource ts;
    private Cursor cursor;
    //field for passing the variable to the next activity
    public static final String name ="Name";
    public static final String sT = "StartTime";
    public static final String eT = "EndTime";
    public static final String ID = "ID";
    public static final String TID = "TID";
    public static final String meet = "meetTime";
    public TrackingListAdapter(Context context,Cursor cursor,TrackingSource ts){ // List<Tracking> trackingDataset
        this.context  = context;
//        this.trackingDataset = trackingDataset;
        this.cursor = cursor;
        this.ts = ts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.trackable_list_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        Tracking t = trackingDataset.get(i);
//        String sStartTime = TrackingManager.dateformat.format(t.getTargetStartTime());
//        String sEndTime = TrackingManager.dateformat.format(t.getTargetEndTime());
//        String meet = TrackingManager.dateformat.format((t.getMeetTime()));
//
//        viewHolder.title.setText("Title: " + t.getTitle());
//        viewHolder.startTime.setText("Start Time:" + sStartTime );
//        viewHolder.endTime.setText("End Time:" + sEndTime);
//        viewHolder.trackableName.setText("Trackable Name : " + TrackableManager.getName(t.getTrackableId()));
        if(!cursor.moveToPosition(i)){
            return;
        }


        String title = cursor.getString(cursor.getColumnIndex(TrackingTable.COLUMN_TITLE));
        String sStartTime = cursor.getString(cursor.getColumnIndex(TrackingTable.COLUMN_TARGET_START_TIME));//TrackingManager.dateformat.format(t.getTargetStartTime());
        String sEndTime =  cursor.getString(cursor.getColumnIndex(TrackingTable.COLUMN_TARGET_END_TIME));//TrackingManager.dateformat.format(t.getTargetEndTime());
        String meet =  cursor.getString(cursor.getColumnIndex(TrackingTable.COLUMN_MEET_TIME)); //TrackingManager.dateformat.format((t.getMeetTime()));
        int trackableid =  cursor.getInt(cursor.getColumnIndex(TrackingTable.COLUMN_TRACK_ID));
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        System.out.println("Trackable ID " + trackableid + ",  id  is " + id);
        String trackingid = cursor.getString(cursor.getColumnIndex(TrackingTable.COLUMN_ID));

        viewHolder.title.setText("Title: " + title);
        viewHolder.startTime.setText("Start Time:" + sStartTime );
        viewHolder.endTime.setText("End Time:" + sEndTime);
        viewHolder.trackableName.setText("Trackable Name : " + TrackableManager.getName(trackableid));

        viewHolder.view.setOnClickListener(new onTrackingItemClickedListener(context,title,sStartTime,sEndTime,trackableid,String.valueOf(id),meet));  // pass to the edit tracking activity
        viewHolder.view.setOnLongClickListener(new OnLongClickToRemoveItem(ts,id,this)); // long click to remove Itemz
    }


    @Override
    public int getItemCount() {  // return trackingDataset.size();
        return cursor.getCount();
    }

    public void swapCursor(Cursor cursor){
        if(this.cursor !=null){
            this.cursor.close();
        }

        this.cursor = cursor;
        if(cursor!=null){
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView startTime;
        public TextView endTime;
        public TextView trackableName;
        public View view;
        public ViewHolder(View v){
            super(v);
            view = v;
            title = v.findViewById(R.id.name);
            startTime = v.findViewById(R.id.category);
            endTime = v.findViewById(R.id.description);
            trackableName = v.findViewById(R.id.url);
        }
    }
}
