package com.zachary.astro.base;

import android.app.Application;
import android.content.res.Resources;

import com.facebook.FacebookSdk;

/**
 * Created by user on 10/5/2017.
 */

public class BaseApplication extends Application {
//    public static int screenWidth;
//    public static int screenHeight;

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
//        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
