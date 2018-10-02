package com.example.pan.assignment1.service;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.pan.assignment1.R;
import com.example.pan.assignment1.model.tracking.MeelEvent;
import com.example.pan.assignment1.model.tracking.Tracking;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TrackingService {
    private static final String LOG_TAG = TrackingService.class.getName();
    private List<Tracking> trackingList = new ArrayList<>();
    private static Context context;

    private TrackingService()
    {
    }

    public List<Tracking> getTrackingList(){ // need to get the time

        return trackingList;
    }
    // check if the source date is with the range of target date +/- minutes and seconds
    private boolean dateInRange(Date source, Date target, int periodMinutes, int periodSeconds)
    {
        Calendar sourceCal = Calendar.getInstance();
        Calendar targetCalStart = Calendar.getInstance();
        Calendar targetCalEnd = Calendar.getInstance();
        // set the calendars for comparison
        sourceCal.setTime(source);
        targetCalStart.setTime(target);
        targetCalEnd.setTime(target);

        // set up start and end range match for mins/secs
        // +/- period minutes/seconds to check
        targetCalStart.set(Calendar.MINUTE, targetCalStart.get(Calendar.MINUTE) - periodMinutes);
        targetCalStart.set(Calendar.SECOND, targetCalStart.get(Calendar.SECOND) - periodSeconds);
        targetCalEnd.set(Calendar.MINUTE, targetCalEnd.get(Calendar.MINUTE) + periodMinutes);
        targetCalEnd.set(Calendar.SECOND, targetCalEnd.get(Calendar.SECOND) + periodMinutes);

        // return if source date in the target range (inclusive of start/end range)
        return sourceCal.equals(targetCalStart) || sourceCal.equals(targetCalEnd)
                || (sourceCal.after(targetCalStart) && sourceCal.before(targetCalEnd));
    }

    // called internally before usage
    private void parseFile(Context context)
    {
        trackingList.clear();
        // resource reference to tracking_data.txt in res/raw/ folder of your project
        // supports trailing comments with //
        try (Scanner scanner = new Scanner(context.getResources().openRawResource(R.raw.tracking_data)))
        {
            // match comma and 0 or more whitespace OR trailing space and newline
            scanner.useDelimiter(",\\s*|\\s*\\n+");
            while (scanner.hasNext())
            {
                Tracking trackingInfo = new MeelEvent();

                //????????????????? which time
                trackingInfo.setTargetStartTime(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).parse(scanner.next()));
                trackingInfo.setTrackableId(Integer.parseInt(scanner.next()));
                trackingInfo.setStopTime(Integer.parseInt(scanner.next())); //????
                trackingInfo.setLatitude(Double.parseDouble(scanner.next()));
                String next=scanner.next();
                int commentPos;
                // strip trailing commenta
                if((commentPos=next.indexOf("//")) >=0)
                    next=next.substring(0, commentPos);
                trackingInfo.setLongitude(Double.parseDouble(next));
                trackingList.add(trackingInfo);
            }
        }
        catch (Resources.NotFoundException e)
        {
            Log.i(LOG_TAG, "File Not Found Exception Caught");
        }
        catch (ParseException e)
        {
            Log.i(LOG_TAG, "ParseException Caught (Incorrect File Format)");
        }

    }


    // singleton support
    private static class LazyHolder
    {
        static final TrackingService INSTANCE = new TrackingService();
    }

    // PUBLIC METHODS

    // singleton
    // thread safe lazy initialisation: see https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    public static TrackingService getSingletonInstance(Context context)
    {
        TrackingService.context = context;
        return LazyHolder.INSTANCE;
    }

    // log contents of file (for testing/logging only)
    public void logAll()
    {
        log(trackingList);
    }

    // log contents of provided list (for testing/logging and example purposes only)
    public void log(List<Tracking> trackingList)
    {
        // we reparse file contents for latest data on every call
        parseFile(context);
        for (Tracking trackingInfo : trackingList)
        {
            // to prevent this logging issue https://issuetracker.google.com/issues/77305804
            try
            {
                Thread.sleep(1);
            }
            catch (InterruptedException e)
            {
            }
            Log.i(LOG_TAG, trackingInfo.toString());
        }
    }

    // the main method you can call periodically to get data that matches a given date period
    // date +/- period minutes/seconds to check
    public List<Tracking> getTrackingInfoForTimeRange(Date date, int periodMinutes, int periodSeconds)
    {
        // we reparse file contents for latest data on every call
        parseFile(context);
        List<Tracking> returnList = new ArrayList<>();
        for (Tracking trackingInfo : trackingList)
            //?????? Which time?
            if (dateInRange(trackingInfo.getTargetStartTime(), date, periodMinutes, periodSeconds))
                returnList.add(trackingInfo);
        return returnList;
    }
}
