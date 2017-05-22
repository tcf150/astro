package com.zachary.astro.service;

import com.zachary.astro.service.model.GetChannelListResponse;
import com.zachary.astro.service.model.GetChannelsResponse;
import com.zachary.astro.service.model.GetEventsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public interface AstroService {
    @GET("v3/getChannelList")
    Call<GetChannelListResponse> getChannelList();

    @GET("v3/getChannels")
    Call<GetChannelsResponse> getChannels();

    @GET("v3/getEvents")
    Call<GetEventsResponse> getEvents(@Query("periodStart") String periodStart, @Query("periodEnd") String periodEnd, @Query("channelId")List<Integer> channelId);

}
