package com.zachary.astro.base;

import android.app.Application;
import android.content.res.Resources;

/**
 * Created by user on 10/5/2017.
 */

public class BaseApplication extends Application {
//    public static int screenWidth;
//    public static int screenHeight;

    @Override
    public void onCreate() {
        super.onCreate();

//        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
