package com.example.pan.assignment1.controller;

import android.location.Location;
import android.os.Bundle;

public class OnLocationListener implements android.location.LocationListener {
    @Override
    public void onLocationChanged(Location location) {
        System.out.println("Loc is " + location.getLatitude() +","+ location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
