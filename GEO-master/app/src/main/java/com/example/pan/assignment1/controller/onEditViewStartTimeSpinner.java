package com.example.pan.assignment1.controller;

import android.view.View;
import android.widget.AdapterView;

import com.example.pan.assignment1.model.tracking.Tracking;
import com.example.pan.assignment1.model.tracking.TrackingManager;

import java.text.ParseException;

public class onEditViewStartTimeSpinner implements AdapterView.OnItemSelectedListener {


    private String trackingId;
    public onEditViewStartTimeSpinner(String id){

        this.trackingId = id;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();

        for (Tracking t : TrackingManager.getTrackingList()) {

            if (t.getTrackingId().equals(trackingId)) {

                try {
                    t.setTargetStartTime(TrackingManager.dateformat.parse(selected));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
