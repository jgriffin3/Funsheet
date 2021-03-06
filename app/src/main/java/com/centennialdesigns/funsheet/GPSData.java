package com.centennialdesigns.funsheet;

import android.content.Context;

import im.delight.android.location.SimpleLocation;

/**
 * Created by WhatWhat on 12/4/2017.
 */

public class GPSData {
    private static GPSData ourInstance;
    private SimpleLocation mLocation;


    public static GPSData getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new GPSData(context);
        }
        return ourInstance;
    }

    private GPSData(Context context) {

        // Params: context, Require Fine Location, Use Passive Mode, Update Interval (ms)
        mLocation = new SimpleLocation(context, true, false, 1000);
        if (!mLocation.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(context);
        }

        startUpdatingGPS();
    }

    public void startUpdatingGPS(){
        mLocation.beginUpdates();
    }

    public void stopUpdatingGPS(){
        mLocation.endUpdates();
    }

    public double getLatitude(){
        return mLocation.getLatitude();
    }

    public double getLongitude() {
        return mLocation.getLongitude();
    }
}
