package com.zachary.astro.model;

import com.zachary.astro.base.BaseModel;
import com.zachary.astro.model.annotation.SSOType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class User extends BaseModel {
    private String socialId;
    @SSOType
    private int ssoType;
    private List<ChannelList> favouriteList;

    public User(){
        favouriteList = new ArrayList<>();
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    @SSOType
    public int getSsoType() {
        return ssoType;
    }

    public void setSsoType(@SSOType int ssoType) {
        this.ssoType = ssoType;
    }

    public List<ChannelList> getFavouriteList() {
        return favouriteList;
    }

    public void setFavouriteList(List<ChannelList> favouriteList) {
        this.favouriteList = favouriteList;
    }
}
