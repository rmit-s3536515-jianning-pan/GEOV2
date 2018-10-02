package com.example.pan.assignment1.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.pan.assignment1.view.fragments.AddTracking;

public class OnClickToAddTrackingActivity implements View.OnClickListener {
    private Context context;
    private int ID;

    public OnClickToAddTrackingActivity(Context context, int ID) {
        this.context = context;
        this.ID = ID;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, AddTracking.class);

        intent.putExtra("TrackableId",Integer.toString(ID));

        context.startActivity(intent);
    }
}
