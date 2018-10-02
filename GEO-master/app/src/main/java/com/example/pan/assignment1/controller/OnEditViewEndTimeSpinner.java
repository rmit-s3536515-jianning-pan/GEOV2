package com.example.pan.assignment1.controller;

import android.view.View;
import android.widget.AdapterView;

import com.example.pan.assignment1.model.tracking.Tracking;
import com.example.pan.assignment1.model.tracking.TrackingManager;

import java.text.ParseException;

public class OnEditViewEndTimeSpinner  implements AdapterView.OnItemSelectedListener {

    private String tId;

    public OnEditViewEndTimeSpinner(String tId){
        this.tId = tId;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();

        for (Tracking t : TrackingManager.getTrackingList()) {

            if (t.getTrackingId().equals(tId)) {

                try {
                    t.setTargetEndTime(TrackingManager.dateformat.parse(selected));
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
