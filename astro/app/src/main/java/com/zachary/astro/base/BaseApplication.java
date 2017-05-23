package com.zachary.astro.base;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.zachary.astro.data.DataManager;
import com.zachary.astro.data.UserManager;
import com.zachary.astro.model.User;

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
        UserManager.getInstance().init(this);
//        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
