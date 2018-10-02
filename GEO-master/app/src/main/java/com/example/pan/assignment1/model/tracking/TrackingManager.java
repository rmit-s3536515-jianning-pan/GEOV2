package com.example.pan.assignment1.model.tracking;

import android.util.Log;
import android.widget.EditText;

import com.example.pan.assignment1.MainActivity;
import com.example.pan.assignment1.service.TrackingService;

import java.nio.channels.CancelledKeyException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrackingManager {

    private static List<Tracking> trackingList = new ArrayList<>();

    public static DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);

    // Sort the array by start time
    public static List<Tracking> getTrackingList() {
        Collections.sort(trackingList, new Comparator<Tracking>() {
            @Override
            public int compare(Tracking o1, Tracking o2) {
                return o1.getTargetStartTime().compareTo(o2.getTargetStartTime());
            }
        });
        return trackingList;
    }

    private static final String TAG = TrackingManager.class.getName();

    public static void displayRoute(int id) {
        List<Tracking> t = TrackingService.getSingletonInstance(MainActivity.getContext()).getTrackingList();
        ;

        for (Tracking tr : t) {

            Log.i(TAG, tr.toString());

        }
    }

    public static void addTracking(Tracking tracking) {
        trackingList.add(tracking);
    }

//    public static List<String> getAllTime() {
//
//        List<String> dateTime = new ArrayList<>();
//
//        List<Tracking> trackings = TrackingService.getSingletonInstance(MainActivity.getContext()).getTrackingList();
//
//        for (Tracking t : trackings) {
//            String sDate = dateformat.format(t.getTargetStartTime());
//
//            dateTime.add(sDate);
//        }
//        Set<String> set = new HashSet<>(dateTime);
//
//        List<String> result = new ArrayList<>(set);
//        Collections.sort(result);
//        return result;
//    }

    public static List<String> getStartTime(String extra) {
        List<String> dateTime = new ArrayList<>();

        List<Tracking> trackings = TrackingService.getSingletonInstance(MainActivity.getContext()).getTrackingList();

        for (Tracking t : trackings) {
            if (t.getTrackableId() == Integer.parseInt(extra)) { // if two trackable id is same
                if (t.getStopTime() > 0) {
                    String sDate = dateformat.format(t.getTargetStartTime());
                    dateTime.add(sDate);
                }
            }


        }

        return dateTime;
    }

    //
    public static String setEndTime(String extra,String date) {


        List<Tracking> trackings = TrackingService.getSingletonInstance(MainActivity.getContext()).getTrackingList();
        String n = "";
        for (Tracking t : trackings) {
            if (t.getTrackableId() == Integer.parseInt(extra)) { // if two trackable id is same
                if (t.getStopTime() > 0) {
                    String sDate = dateformat.format(t.getTargetStartTime());
                    if(sDate.equals(date)){
                        Date d = t.getTargetStartTime();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(d);

                        calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+t.getStopTime());
                        n = dateformat.format(calendar.getTime());

                        break;
                    }



                }
            }

        }
        return n;
    }

    public static List<String> getMeetTime(String starttime, String endtime){
        List<String> meet = new ArrayList<>();

        Calendar c = Calendar.getInstance();
//        meet.add(starttime);
        boolean running = true;
        try {
            Date s = dateformat.parse(starttime);
            Date e = dateformat.parse(endtime);
            c.setTime(s);

            while(running){

                meet.add(dateformat.format(c.getTime()));
                c.add(Calendar.MINUTE,1);

                Date current = c.getTime();

                if(current.compareTo(e)>0){
                    running = false;
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return meet;
    }
}
