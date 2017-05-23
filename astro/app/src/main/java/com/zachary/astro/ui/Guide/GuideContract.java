package com.zachary.astro.ui.Guide;

import com.zachary.astro.base.BasePresenter;
import com.zachary.astro.base.BaseView;
import com.zachary.astro.model.Events;
import com.zachary.astro.model.annotation.SortType;

import java.util.List;

/**
 * Created by user on 22/5/2017.
 */

public interface GuideContract {
    interface View extends BaseView<Presenter> {
        void clearEventList();
        void addEventList(List<Events> eventsList);
        void showProgress(boolean show);
    }

    interface Presenter extends BasePresenter {
        void getEventList(boolean isBegin);
        void setSortType(@SortType int sortType);
    }
}
