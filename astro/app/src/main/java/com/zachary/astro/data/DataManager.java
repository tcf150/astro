package com.zachary.astro.data;

import com.zachary.astro.model.ChannelList;
import com.zachary.astro.model.annotation.SortType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 22/5/2017.
 */

public class DataManager {
    private final static DataManager instance = new DataManager();

    private List<ChannelList> channelList = new ArrayList<>();
    private int channelSortType = -1;

    public DataManager(){}

    public static DataManager getInstance() {
        return instance;
    }

    public List<ChannelList> getChannelListByName(){
        if (channelSortType != SortType.ByName) {
            Collections.sort(channelList, new Comparator<ChannelList>() {
                public int compare(ChannelList obj1, ChannelList obj2) {
                    return obj1.getChannelTitle().compareToIgnoreCase(obj2.getChannelTitle()); // To compare string values
                }
            });
            channelSortType = SortType.ByName;
        }
        return channelList;
    }

    public List<ChannelList> getChannelListByNumber(){
        if (channelSortType != SortType.ByNumber) {
            Collections.sort(channelList, new Comparator<ChannelList>() {
                public int compare(ChannelList obj1, ChannelList obj2) {
                    return Integer.valueOf(obj1.getChannelStbNumber()).compareTo(obj2.getChannelStbNumber()); // To compare string values
                }
            });
            channelSortType = SortType.ByNumber;
        }
        return channelList;
    }

    public int getChannelListSize(){
        return channelList.size();
    }

    public void setChannelList(List<ChannelList> channelList){
        this.channelList.addAll(channelList);
    }

    public void updateChannelList(int channelId, boolean favourite){
        for (ChannelList channel : channelList){
            if (channel.getChannelId() == channelId){
                channel.setFavourite(favourite);
                break;
            }
        }
    }

    public void clearChannelListFavourite(){
        for (ChannelList channel : channelList){
            channel.setFavourite(false);
        }
    }

}
