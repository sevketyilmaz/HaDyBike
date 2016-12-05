package com.hacktusdynamics.android.hadybike;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();

    public static Context sApplicationContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();
        SharedPreferences sp = sApplicationContext.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);

        // if app runs first time (!PREF_USER_EXIST)
        if(!sp.contains(Constants.PREF_USER_EXIST)){
            SharedPreferences.Editor spe = sp.edit();
            spe.putBoolean(Constants.PREF_USER_EXIST, true);
            spe.commit();
            Log.d(TAG, "PREF_USER_EXIST = true");
        }
        Log.d(TAG, "onCreate...");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory...");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTermintae...");
    }
}
