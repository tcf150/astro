package com.zachary.astro.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.zachary.astro.model.Channel;
import com.zachary.astro.model.ChannelList;
import com.zachary.astro.model.User;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class UserManager {
    private final static String USER_BUNDLE = "userBundle";
    private final static String USER_ID = "userId";

    private final static UserManager ourInstance = new UserManager();

    private User user;
    private SharedPreferences sharedPreferences;

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
        user = new User();
    }

    public void init(Context context){
        sharedPreferences = context.getSharedPreferences(USER_BUNDLE,Context.MODE_PRIVATE);
    }

    public String getUserIdCache(){
        return sharedPreferences.getString(USER_ID,"");
    }

    public void setUserIdCache(String userId){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID,userId);
        editor.commit();
    }

    public String getUserId(){
        return user.getUserId();
    }

    public boolean hasUserId(){
        return (user.getUserId() != null && user.getUserId().length() > 0);
    }

    public String getSocialId(){
        return user.getSocialId();
    }

    public List<ChannelList> getFavouriteList(){
        return user.getFavouriteList();
    }

    public ChannelList getFavouriteById(int channelId){
        for (ChannelList channel : user.getFavouriteList()){
            if (channel.getChannelId() == channelId){
                return channel;
            }
        }
        return null;
    }

    public void addFavouriteChannelList(List<ChannelList> channelList){
        if (channelList == null) return;
        this.user.getFavouriteList().clear();
        this.user.getFavouriteList().addAll(channelList);

        DataManager manager = DataManager.getInstance();
        for (ChannelList item : channelList){
            manager.updateChannelList(item.getChannelId(),true);
        }
    }

    public void addFavouriteChannel(ChannelList channel){
        this.user.getFavouriteList().add(channel);
    }

    public void removeFavouriteChannel(int favouriteId){
        Iterator<ChannelList> iterator = user.getFavouriteList().iterator();
        while (iterator.hasNext()){
            ChannelList item = iterator.next();
            if (item.getFavouriteId() == favouriteId){
                iterator.remove();
            }
        }
    }

    public void setUser(User user){
        this.user.setUserId(user.getUserId());
        this.user.setSocialId(user.getSocialId());
        this.user.setSsoType(user.getSsoType());
        this.user.getFavouriteList().addAll(user.getFavouriteList());
    }

    public boolean isFavourited(int channelId){
        List<ChannelList> channelList = user.getFavouriteList();
        if (channelList == null || channelList.size() == 0) return false;
        for (ChannelList item : channelList){
            if (item.getChannelId() == channelId){
                return true;
            }
        }
        return false;
    }

    private static String hashKey(String value){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(value.getBytes());

            StringBuffer buffer = new StringBuffer();
            for (byte bt : digest.digest()){
                String hex = Integer.toHexString(0xFF & bt);
                if (hex.length() == 1) buffer.append('0');
                buffer.append(hex);
            }
            return buffer.toString();
        }catch (Exception e){
            e.printStackTrace();
            return value;
        }
    }
}
