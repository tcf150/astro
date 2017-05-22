package com.zachary.astro.data;

import com.zachary.astro.model.Channel;
import com.zachary.astro.model.ChannelList;
import com.zachary.astro.model.User;

import java.util.List;

/**
 * Created by tongcheefei on 22/05/2017.
 */

class UserManager {
    private static final UserManager ourInstance = new UserManager();

    private User user;

    static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
        user = new User();
    }

    public String getUserId(){
        return user.getSocialId();
    }

    public List<ChannelList> getFavouriteList(){
        return user.getFavouriteList();
    }

    public void addFavouriteChannel(ChannelList channel){
        this.user.getFavouriteList().add(channel);
    }

    public void removeFavouriteChannel(ChannelList channel){
        this.user.getFavouriteList().remove(channel);
    }

    public void setUser(User user){
        this.user.setSocialId(user.getSocialId());
        this.user.setSsoType(user.getSsoType());
        this.user.getFavouriteList().addAll(user.getFavouriteList());
    }
}