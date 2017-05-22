package com.zachary.astro.service.model;

import com.zachary.astro.base.BaseResponse;
import com.zachary.astro.model.ChannelList;

import java.util.List;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class GetChannelListResponse extends BaseResponse {
    private List<ChannelList> channels;

    public List<ChannelList> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelList> channels) {
        this.channels = channels;
    }
}
