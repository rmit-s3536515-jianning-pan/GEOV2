package com.example.pan.assignment1.helper;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pan.assignment1.R;
import com.example.pan.assignment1.model.tracking.Tracking;
import com.example.pan.assignment1.model.tracking.TrackingManager;

import java.util.List;

public class InputValidationCheck {

    public static boolean checkTrackingTitleIsEmpty(Context context , EditText et){
        if (TextUtils.isEmpty(et.getText())) {
            Toast toast = Toast.makeText(context, R.string.add_title_error, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            return true;
        }
        return false;

    }

    public static boolean checkDuplicateTrackingTitle(Context context,EditText et){
        List<Tracking> lists = TrackingManager.getTrackingList();
        for(Tracking t : lists){
            if(t.getTitle().equals(et.getText().toString())){
                Toast toast = Toast.makeText(context, R.string.title_duplicate_error, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                return true;
            }
        }
        return false;

    }
}
