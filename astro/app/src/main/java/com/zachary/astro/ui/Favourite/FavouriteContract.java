package com.zachary.astro.ui.Favourite;

import com.zachary.astro.base.BasePresenter;
import com.zachary.astro.base.BaseView;
import com.zachary.astro.model.ChannelList;
import com.zachary.astro.model.annotation.SortType;

import java.util.List;

/**
 * Created by user on 23/5/2017.
 */

public interface FavouriteContract {
    interface View extends BaseView<Presenter> {
        void displayChannel(List<ChannelList> channelList);
    }

    interface Presenter extends BasePresenter {
        void favouriteChannel(ChannelList channel);
        void updateChannelSort(@SortType int sortType);
    }
}
