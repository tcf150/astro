package com.zachary.astro.model;

import com.zachary.astro.base.BaseModel;
import com.zachary.astro.model.annotation.ChannelExtRefSubSystem;

import java.util.Date;
import java.util.List;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class Channel extends BaseModel {
    private int channelId;
    private String channelTitle;
    private String channelDescription;
    private String channelLanguage;
    private String channelColor1;
    private String channelColor2;
    private String channelColor3;
    private String channelCategory;
    private String channelStbNumber;
    private boolean channelHD;
    private int hdSimulcastChannel;
    private Date channelStartDate;
    private Date channelEndDate;
    private List<ChannelExtRef> channelExtRef;
    private List<LinearOttMapping> linearOttMapping;

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

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public String getChannelLanguage() {
        return channelLanguage;
    }

    public void setChannelLanguage(String channelLanguage) {
        this.channelLanguage = channelLanguage;
    }

    public String getChannelColor1() {
        return channelColor1;
    }

    public void setChannelColor1(String channelColor1) {
        this.channelColor1 = channelColor1;
    }

    public String getChannelColor2() {
        return channelColor2;
    }

    public void setChannelColor2(String channelColor2) {
        this.channelColor2 = channelColor2;
    }

    public String getChannelColor3() {
        return channelColor3;
    }

    public void setChannelColor3(String channelColor3) {
        this.channelColor3 = channelColor3;
    }

    public String getChannelCategory() {
        return channelCategory;
    }

    public void setChannelCategory(String channelCategory) {
        this.channelCategory = channelCategory;
    }

    public String getChannelStbNumber() {
        return channelStbNumber;
    }

    public void setChannelStbNumber(String channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public boolean isChannelHD() {
        return channelHD;
    }

    public void setChannelHD(boolean channelHD) {
        this.channelHD = channelHD;
    }

    public int getHdSimulcastChannel() {
        return hdSimulcastChannel;
    }

    public void setHdSimulcastChannel(int hdSimulcastChannel) {
        this.hdSimulcastChannel = hdSimulcastChannel;
    }

    public Date getChannelStartDate() {
        return channelStartDate;
    }

    public void setChannelStartDate(Date channelStartDate) {
        this.channelStartDate = channelStartDate;
    }

    public Date getChannelEndDate() {
        return channelEndDate;
    }

    public void setChannelEndDate(Date channelEndDate) {
        this.channelEndDate = channelEndDate;
    }

    public List<ChannelExtRef> getChannelExtRef() {
        return channelExtRef;
    }

    public void setChannelExtRef(List<ChannelExtRef> channelExtRef) {
        this.channelExtRef = channelExtRef;
    }

    public ChannelExtRef getChannelExfRefBySubSystem(@ChannelExtRefSubSystem String subSystem){
        if (channelExtRef == null || channelExtRef.size() == 0) return null;

        for (ChannelExtRef item : channelExtRef){
            if (item.getSubSystem().equals(subSystem)){
                return item;
            }
        }
        return null;
    }

    public List<LinearOttMapping> getLinearOttMapping() {
        return linearOttMapping;
    }

    public void setLinearOttMapping(List<LinearOttMapping> linearOttMapping) {
        this.linearOttMapping = linearOttMapping;
    }
}
