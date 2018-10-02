package com.example.pan.assignment1.controller;

import android.view.View;

import com.example.pan.assignment1.model.tracking.TrackingManager;

public class OnLongClickToDisplayRoute implements View.OnLongClickListener{

    private int ID;

    public OnLongClickToDisplayRoute(int ID) {
        this.ID = ID;
    }

    @Override
    public boolean onLongClick(View v) {
        TrackingManager.displayRoute(ID);
        return true;
    }
}
