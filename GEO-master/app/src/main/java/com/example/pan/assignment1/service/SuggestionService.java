package com.example.pan.assignment1.service;

import android.Manifest;
import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.pan.assignment1.MainActivity;
import com.example.pan.assignment1.controller.OnLocationListener;


public class SuggestionService extends IntentService {

    public static final String TAG = "SuggestionService Class";
    private LocationManager locationmgr;

    public SuggestionService() {
        super("Suggesion Service");
        Log.d(TAG, "Suggesion Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        getGpsLocation();
        Log.d(TAG, "onHandleIntent");
    }

    private void getGpsLocation() {
        locationmgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Intent intent = new Intent(MainActivity.getContext(), LocationService.class);
        PendingIntent i = PendingIntent.getBroadcast(MainActivity.getContext(), 0, intent, 0);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            // Permission is not granted
            // Should we show an explanation?
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)MainActivity.getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                System.out.println("Location permission request");
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions((Activity)MainActivity.getContext(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);


            }

            return;
        }
//        locationmgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        Looper.loop();
        locationmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0,new OnLocationListener());
//        locationmgr.requestSingleUpdate(LocationManager.GPS_PROVIDER, i);


    }



}
