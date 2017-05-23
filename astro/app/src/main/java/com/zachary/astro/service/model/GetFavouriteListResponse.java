package com.zachary.astro.service.model;

import com.zachary.astro.base.BaseResponse;
import com.zachary.astro.model.ChannelList;

import java.util.List;

/**
 * Created by user on 23/5/2017.
 */

public class GetFavouriteListResponse extends BaseResponse {
    private List<ChannelList> favouriteList;

    public List<ChannelList> getFavouriteList() {
        return favouriteList;
    }

    public void setFavouriteList(List<ChannelList> favouriteList) {
        this.favouriteList = favouriteList;
    }
}
