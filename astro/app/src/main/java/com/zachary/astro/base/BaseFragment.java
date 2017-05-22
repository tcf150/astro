package com.zachary.astro.base;


import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by user on 10/5/2017.
 */

public class BaseFragment extends Fragment {
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
}
