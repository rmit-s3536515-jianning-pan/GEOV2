package com.example.pan.assignment1.controller;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.pan.assignment1.model.tracking.Tracking;
import com.example.pan.assignment1.model.tracking.TrackingManager;
import com.example.pan.assignment1.view.fragments.EditTrackingActivity;


public class onTextChangedListener implements TextWatcher{

    private String id;
    private EditText et;

    public onTextChangedListener(String id,EditText et){
        this.id = id;
        this.et = et;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String value = s.toString();

        et.removeTextChangedListener(this);
        et.setText(value);
        et.setSelection(et.getText().length());

        for(Tracking t : TrackingManager.getTrackingList()){
            if(t.getTrackingId().equals(id))

                t.setTitle(value);
        }
        et.addTextChangedListener(this);
    }

}
