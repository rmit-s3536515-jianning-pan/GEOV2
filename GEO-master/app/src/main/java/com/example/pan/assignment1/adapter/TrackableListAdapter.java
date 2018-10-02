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
import android.widget.Toast;

import com.example.pan.assignment1.R;
import com.example.pan.assignment1.controller.OnClickToAddTrackingActivity;
import com.example.pan.assignment1.controller.OnLongClickToDisplayRoute;
import com.example.pan.assignment1.database.TrackableTable;
import com.example.pan.assignment1.model.trackable.Trackable;

import com.example.pan.assignment1.model.tracking.TrackingManager;
import com.example.pan.assignment1.view.fragments.AddTracking;

import java.util.List;

public class TrackableListAdapter extends RecyclerView.Adapter<TrackableListAdapter.ViewHolder> {

    private Context context;
//    private static List<Trackable> dataset;
    private Cursor cursor;
    public TrackableListAdapter(Context context, Cursor cursor){ // List<Trackable> dataset
        this.context = context;
//        this.dataset = dataset;
        this.cursor = cursor;
    }


//    public static void updateDataset(Cursor newCursor){ //update dataset  List<Trackable> data
////        swapCursor(newCursor)
////        dataset = data;
//
//    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.trackable_list_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
//        final Trackable item = dataset.get(i);
//        viewHolder.name.setText("Name : " + item.getName());
//        viewHolder.category.setText("Category : " +item.getCategory());
//        viewHolder.description.setText("Description : " +item.getDescription());
//        viewHolder.url.setText("URL : " + item.getUrl());

        if(!cursor.moveToPosition(i)){
            return;
        }
        int trackableID =cursor.getInt(cursor.getColumnIndex(TrackableTable.COLUMN_ID)); //  int trackableID = item.getId();
        viewHolder.name.setText("Name : " + cursor.getString(cursor.getColumnIndex(TrackableTable.COLUMN_NAME)));
        viewHolder.category.setText("Category : " +cursor.getString(cursor.getColumnIndex(TrackableTable.COLUMN_CATEGORY)));
        viewHolder.description.setText("Description : " +cursor.getString(cursor.getColumnIndex(TrackableTable.COLUMN_DESCRIPTION)));
        viewHolder.url.setText("URL : " + cursor.getString(cursor.getColumnIndex(TrackableTable.COLUMN_URL)));
        System.out.println("Data Id " + trackableID);
        //Click to go to add tracking activity
        viewHolder.view.setOnClickListener(new OnClickToAddTrackingActivity(context,trackableID));

        viewHolder.view.setOnLongClickListener(new OnLongClickToDisplayRoute(trackableID)); // long click to show route information
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }//return dataset.size();

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
        public TextView name;
        public TextView category;
        public TextView description;
        public TextView url;
        public View view;
        public ViewHolder(View v){
            super(v);
            view = v;
            name = v.findViewById(R.id.name);
            category = v.findViewById(R.id.category);
            description = v.findViewById(R.id.description);
            url = v.findViewById(R.id.url);
        }
    }

}
