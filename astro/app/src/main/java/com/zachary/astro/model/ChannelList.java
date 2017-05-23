package com.zachary.astro.model;

import com.zachary.astro.base.BaseModel;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class ChannelList extends BaseModel {
    private int favouriteId;
    private int channelId;
    private String channelTitle;
    private int channelStbNumber;
    private boolean isFavourite;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public int getChannelStbNumber() {
        return channelStbNumber;
    }

    public void setChannelStbNumber(int channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public int getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(int favouriteId) {
        this.favouriteId = favouriteId;
    }
}
