package com.example.pan.assignment1.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pan.assignment1.helper.InputValidationCheck;
import com.example.pan.assignment1.model.tracking.Tracking;
import com.example.pan.assignment1.model.tracking.TrackingManager;
import com.example.pan.assignment1.view.fragments.TrackingList;

import java.text.ParseException;

public class OnEditTrackingButtonClickedListener implements View.OnClickListener {

    private Context context;
    private String tId;
    private EditText et;
    private Spinner s;
    private EditText e;
    private Spinner m;
    public OnEditTrackingButtonClickedListener(Context context, String tId, EditText et,Spinner s,EditText e, Spinner m) {
        this.context = context;
        this.tId = tId;
        this.et = et;
        this.s = s;
        this.e = e;
        this.m = m;
    }

    @Override
    public void onClick(View v) {
        if (!InputValidationCheck.checkTrackingTitleIsEmpty(context,et)) {
            String start = s.getSelectedItem().toString();
            String end = e.getText().toString();
            String meet = m.getSelectedItem().toString();

            Intent intent = new Intent(context, TrackingList.class);
            for (Tracking t : TrackingManager.getTrackingList()) {

                if (t.getTrackingId().equals(tId)) {

                    try {
                        t.setTargetStartTime(TrackingManager.dateformat.parse(start));
                        t.setTargetEndTime(TrackingManager.dateformat.parse(end));
                        t.setMeetTime(TrackingManager.dateformat.parse(meet));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }
            context.startActivity(intent);
        }
    }
}

