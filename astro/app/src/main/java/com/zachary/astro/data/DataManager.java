package com.zachary.astro.data;

import com.zachary.astro.model.ChannelList;

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

    public DataManager(){}

    public static DataManager getInstance() {
        return instance;
    }

    public List<ChannelList> getChannelListByName(){
        Collections.sort(channelList, new Comparator<ChannelList>(){
            public int compare(ChannelList obj1, ChannelList obj2) {
                return obj1.getChannelTitle().compareToIgnoreCase(obj2.getChannelTitle()); // To compare string values
            }
        });
        return channelList;
    }

    public List<ChannelList> getChannelListByNumber(){
        Collections.sort(channelList, new Comparator<ChannelList>(){
            public int compare(ChannelList obj1, ChannelList obj2) {
                return Integer.valueOf(obj1.getChannelStbNumber()).compareTo(obj2.getChannelStbNumber()); // To compare string values
            }
        });
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

}
