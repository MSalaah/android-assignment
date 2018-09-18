package com.salah.instabridge.controller;

import android.app.Application;
import android.content.res.Configuration;


/**
 * Created by salah on 9/15/18.
 */

public class AppController extends Application {

    private static AppController mInstance;

    public static synchronized AppController getInstance() {
        if (mInstance == null) {
            mInstance = new AppController();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
