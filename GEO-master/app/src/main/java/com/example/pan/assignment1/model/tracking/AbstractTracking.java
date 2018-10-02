package com.example.pan.assignment1.model.tracking;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public abstract class AbstractTracking implements Tracking{
    private String trackingId;
    private int trackableId;
    private String title;
    private Date targetStartTime;
    private Date targetEndTime;
    private Date meetTime;
    private double latitude;
    private double longitude; // current locaton
    private int stopTime; //meet Location

    public AbstractTracking(){
        trackingId = UUID.randomUUID().toString();
    }

    @Override
    public String getTrackingId() {
        return trackingId;
    }



    @Override
    public int getTrackableId() {
        return trackableId;
    }

    @Override
    public void setTrackableId(int trackableId) {
            this.trackableId = trackableId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getTargetStartTime() {
        return targetStartTime;
    }

    @Override
    public void setTargetStartTime(Date targetStartTime) {
        this.targetStartTime = targetStartTime;
    }

    @Override
    public Date getTargetEndTime() {
        return targetEndTime;
    }

    @Override
    public void setTargetEndTime(Date targetEndTime) {
        this.targetEndTime = targetEndTime;
    }

    @Override
    public Date getMeetTime() {
        return meetTime;
    }

    @Override
    public void setMeetTime(Date meetTime) {
            this.meetTime = meetTime;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int getStopTime() {
        return stopTime;
    }

    @Override
    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }




    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Date/Time=%s, trackableId=%d, stopTime=%d, lat=%.5f, long=%.5f", DateFormat.getDateTimeInstance(
                DateFormat.SHORT, DateFormat.MEDIUM).format(targetStartTime), trackableId, stopTime, latitude, longitude);
    }
}
