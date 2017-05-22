package com.zachary.astro.service.model;

import com.zachary.astro.base.BaseResponse;
import com.zachary.astro.model.Channel;

import java.util.List;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class GetChannelsResponse extends BaseResponse {
    private List<Channel> channel;

    public List<Channel> getChannel() {
        return channel;
    }

    public void setChannel(List<Channel> channel) {
        this.channel = channel;
    }
}
