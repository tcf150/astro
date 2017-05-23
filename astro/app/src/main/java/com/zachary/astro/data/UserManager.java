package com.zachary.astro.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBDeleteExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedQueryList;
import com.amazonaws.models.nosql.FavouriteDO;
import com.amazonaws.models.nosql.UserDO;
import com.zachary.astro.model.Channel;
import com.zachary.astro.model.ChannelList;
import com.zachary.astro.model.User;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
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

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
        user = new User();
    }

    public static String getUserIdCache(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_BUNDLE,Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_ID,"");
    }

    public static void setUserIdCache(Context context,String userId){
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_BUNDLE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID,userId);
        editor.commit();
    }

    public static User getUserDetail(String userId){
       return null;
    }

    public static List<ChannelList> getUserFavouriteList(String userId){
        List<ChannelList> channelList = new ArrayList<>();
        return channelList;
    }

    public static String createUser(User user){
        return null;
    }

    private void likeChannel(ChannelList channel){

    }

    private void unlikeChannel(ChannelList channel){

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

    public void addFavouriteChannel(ChannelList channel){
        likeChannel(channel);
        this.user.getFavouriteList().add(channel);
    }

    public void removeFavouriteChannel(ChannelList channel){
        unlikeChannel(channel);
        this.user.getFavouriteList().remove(channel);
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
