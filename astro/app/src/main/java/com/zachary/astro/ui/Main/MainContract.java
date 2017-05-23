package com.zachary.astro.ui.Main;

import android.support.v4.app.Fragment;

import com.facebook.CallbackManager;
import com.zachary.astro.base.BasePresenter;
import com.zachary.astro.base.BaseView;
import com.zachary.astro.model.ChannelList;
import com.zachary.astro.model.annotation.SortType;

import java.util.List;

/**
 * Created by user on 22/5/2017.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void displayChannel(List<ChannelList> channelList);
        void displayLoginDialog();
        void gotoFavourite();
        void showLoading(boolean show);
    }

    interface Presenter extends BasePresenter {
        void onFavouriteMenuClick();
        void favouriteChannel(ChannelList channel);
        void getChannelList();
        void updateChannelSort(@SortType int sortType);
        void facebookLogin(Fragment fragment, CallbackManager callbackManager);
        void googleLogin(String socialId);
        void logout();
    }
}
