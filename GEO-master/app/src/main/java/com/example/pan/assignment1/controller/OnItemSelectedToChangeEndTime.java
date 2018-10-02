package com.example.pan.assignment1.controller;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pan.assignment1.model.tracking.TrackingManager;

public class OnItemSelectedToChangeEndTime implements AdapterView.OnItemSelectedListener {

    private Context context;
    private EditText edit;
    private  String ID;
    private Spinner meet;
    private  String meetTime;
    public OnItemSelectedToChangeEndTime(Context context, EditText edit, String ID, Spinner meet,String meetTime) {
        this.context = context;
        this.edit = edit;
        this.ID = ID;
        this.meet = meet;
        this.meetTime = meetTime;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String i = parent.getItemAtPosition(position).toString();
        String end = TrackingManager.setEndTime(ID,i);
        edit.setText(end);
        ArrayAdapter<String> spinneradapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,TrackingManager.getMeetTime(i,end));
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meet.setAdapter(spinneradapter);
        if(meetTime!=null){
            meet.setSelection(spinneradapter.getPosition(meetTime));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
