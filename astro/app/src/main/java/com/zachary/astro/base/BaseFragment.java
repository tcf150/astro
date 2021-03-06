package com.zachary.astro.base;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.zachary.astro.R;

/**
 * Created by user on 10/5/2017.
 */

public class BaseFragment extends Fragment {
    protected final static int PERMISSION_REQUEST_CODE = 111;
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

    /**
     * hide the Keyboard from any focus
     */
    protected void hideKeyboard(){
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * check permission
     * @param permission Manifest.permission
     * @return boolean
     */
    protected boolean checkAppPermission(@NonNull String permission){
        switch (ContextCompat.checkSelfPermission(getActivity(),permission)){
            case PackageManager.PERMISSION_GRANTED:
                return true;
            default:
                return false;
        }
    }

    protected boolean checkAppPermissionRationale(@NonNull String permission){
        return ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),permission);
    }

    protected void requestAppPermission(@NonNull String permission, int requestCode){
        ActivityCompat.requestPermissions(getActivity(),new String[]{permission},requestCode);
    }

    protected void showAppPermissionDialog(@StringRes int resId){
        showAppPermissionDialog(resId,null);
    }

    protected void showAppPermissionDialog(@StringRes int resId, @Nullable DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.permission_title);
        builder.setPositiveButton(R.string.permission_update, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",getContext().getPackageName(),null);
                intent.setData(uri);
                startActivityForResult(intent,PERMISSION_REQUEST_CODE);
            }
        });
        builder.setNegativeButton(R.string.permission_not_now,onClickListener);
        builder.setMessage(resId);

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
