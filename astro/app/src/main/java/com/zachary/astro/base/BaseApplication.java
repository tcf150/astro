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

        String cacheUserId = UserManager.getUserIdCache(this);
        if (cacheUserId != null && cacheUserId.length() > 0){
            User user = UserManager.getUserDetail(cacheUserId);
            if (user != null){
                UserManager.getInstance().setUser(user);
            }else{
                UserManager.setUserIdCache(this,"");
            }
        }
//        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
