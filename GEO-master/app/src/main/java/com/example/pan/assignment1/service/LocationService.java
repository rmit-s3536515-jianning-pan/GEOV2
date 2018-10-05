package com.example.pan.assignment1.service;

import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class LocationService extends BroadcastReceiver {


    public LocationService() {
//        super("Location");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Location Service");
        Bundle bundle =intent.getExtras();
        Location location = (Location) bundle.get(LocationManager.KEY_LOCATION_CHANGED);
        System.out.println("Loc is " + location.getLatitude() +","+ location.getLongitude());

    }

//
//    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
//
//    }
}
