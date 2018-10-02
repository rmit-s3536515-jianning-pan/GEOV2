package com.example.pan.assignment1.model.tracking;

import java.util.Date;

public interface Tracking {
    String getTrackingId();

//    void setTrackingId(String trackingId);

    int getTrackableId();

    void setTrackableId(int trackableId);

    String getTitle();

    void setTitle(String title);

    Date getTargetStartTime();

    void setTargetStartTime(Date targetStartTime);

    Date getTargetEndTime();

    void setTargetEndTime(Date targetEndTime);

    Date getMeetTime();

    void setMeetTime(Date meetTime);

    double getLatitude();

    void setLatitude(double latitude);

    double getLongitude();

    void setLongitude(double longitude);

    int getStopTime();

    void setStopTime(int stopTime);
}
