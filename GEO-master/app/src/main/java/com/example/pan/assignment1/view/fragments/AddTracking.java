package com.example.pan.assignment1.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pan.assignment1.R;
import com.example.pan.assignment1.controller.OnClickAddTrackingListener;
import com.example.pan.assignment1.controller.OnItemSelectedToChangeEndTime;
import com.example.pan.assignment1.controller.OnSpinnerItemSelectedListener;
import com.example.pan.assignment1.database.TrackingSource;
import com.example.pan.assignment1.model.tracking.MeelEvent;
import com.example.pan.assignment1.model.tracking.Tracking;
import com.example.pan.assignment1.model.tracking.TrackingManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddTracking extends AppCompatActivity {

    private EditText et;

    private TrackingSource ts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tracking);

        ts = new TrackingSource(this);
        ts.execute();

        init();

    }



    private void init(){
        String extra = getIntent().getStringExtra("TrackableId");

        et = findViewById(R.id.trackingNameField);
        et.setSelection(et.getText().length());

        EditText endTime = findViewById(R.id.endTime);
        endTime.setEnabled(false);

        List<String> start = TrackingManager.getStartTime(extra);
        Spinner startTime = findViewById(R.id.startTime);
        Spinner meetTime = findViewById(R.id.meetTimeSpinner);

        ArrayAdapter<String> spinneradapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,start);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startTime.setAdapter(spinneradapter);
        startTime.setOnItemSelectedListener(new OnItemSelectedToChangeEndTime(this,endTime,extra,meetTime,null));


//        meetTime.setAdapter(spinneradapter);

        Button addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new OnClickAddTrackingListener(this,startTime,endTime,et,extra,meetTime,ts));

    }
}
