package com.zachary.astro.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.zachary.astro.R;

/**
 * Created by user on 10/5/2017.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupContentView();
        setupUI();
    }

    /**
     * setup the content layout
     */
    protected abstract void setupContentView();

    /**
     * setup ui logic
     */
    protected abstract void setupUI();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * get view by using id
     * @param resId view id
     * @param <T> view class
     * @return T type View
     */
    protected <T extends View> T getView(@IdRes int resId){
        return (T) findViewById(resId);
    }

    /**
     * get view from view by using id
     * @param view rootView
     * @param resId view id
     * @param <T> view class
     * @return T type View
     */
    protected <T extends View> T getViewByView(View view, @IdRes int resId){
        return (T) view.findViewById(resId);
    }
}
