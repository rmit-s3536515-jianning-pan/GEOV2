package com.example.pan.assignment1.view.fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pan.assignment1.R;
import com.example.pan.assignment1.adapter.TrackingListAdapter;
import com.example.pan.assignment1.controller.OnEditTrackingButtonClickedListener;
import com.example.pan.assignment1.controller.OnEditViewEndTimeSpinner;
import com.example.pan.assignment1.controller.OnEditViewMeetTimeSpinner;
import com.example.pan.assignment1.controller.OnItemSelectedToChangeEndTime;
import com.example.pan.assignment1.controller.onEditViewStartTimeSpinner;
import com.example.pan.assignment1.controller.onTextChangedListener;
import com.example.pan.assignment1.model.tracking.Tracking;
import com.example.pan.assignment1.model.tracking.TrackingManager;

import java.text.ParseException;
import java.util.List;

public class EditTrackingActivity extends AppCompatActivity {
    private EditText text;
    private Button btn;
    private Spinner startTime;
    private EditText endtime;
    private Spinner meetTime;
    private onTextChangedListener tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tracking); //use the add tracking layout instead of "activity_edit_tracking"

        init();
    }

    private void init() {
        Intent ref = getIntent();
        String name = ref.getStringExtra(TrackingListAdapter.name);
        String sT = ref.getStringExtra(TrackingListAdapter.sT);
//        String eT = ref.getStringExtra(TrackingListAdapter.eT);
        String tId = ref.getStringExtra(TrackingListAdapter.ID);
        String meet = ref.getStringExtra(TrackingListAdapter.meet);
        String trackingId = ref.getStringExtra(TrackingListAdapter.TID);
        text = findViewById(R.id.trackingNameField);
        text.setText(name);
        text.setSelection(text.getText().length()); // make the cursor to the end

        btn = findViewById(R.id.addBtn);
        btn.setText("Edit Tracking");

        tw = new onTextChangedListener(trackingId,text);

        text.addTextChangedListener(tw);




        //spinner

        System.out.println("Trackable ID IN THE EDIT : " + tId );
        List<String> datetime = TrackingManager.getStartTime(tId);

        endtime = findViewById(R.id.endTime);
        endtime.setEnabled(false);

        startTime = findViewById(R.id.startTime);
        meetTime = findViewById(R.id.meetTimeSpinner);


        ArrayAdapter<String> spinneradapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datetime);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        startTime.setAdapter(spinneradapter);
        startTime.setSelection(spinneradapter.getPosition(sT));
//        startTime.setOnItemSelectedListener(new onEditViewStartTimeSpinner(tId));
        startTime.setOnItemSelectedListener(new OnItemSelectedToChangeEndTime(this,endtime,tId,meetTime,meet));

//        meetTime.setSelection();

//
//        endtime.setAdapter(spinneradapter);
//        endtime.setSelection(spinneradapter.getPosition(eT));
//        endtime.setOnItemSelectedListener(new OnEditViewEndTimeSpinner(tId));


//        meetTime.setAdapter(spinneradapter);
//        meetTime.setSelection(spinneradapter.getPosition(meet));
//        meetTime.setOnItemSelectedListener(new OnEditViewMeetTimeSpinner(tId));

        btn.setOnClickListener(new OnEditTrackingButtonClickedListener(this,trackingId,text,startTime,endtime, meetTime));
    }


}
