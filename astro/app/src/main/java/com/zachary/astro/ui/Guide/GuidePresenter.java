package com.zachary.astro.ui.Guide;

import android.util.Log;

import com.zachary.astro.base.BaseApiClient;
import com.zachary.astro.data.DataManager;
import com.zachary.astro.model.ChannelList;
import com.zachary.astro.model.annotation.SortType;
import com.zachary.astro.service.model.GetEventsResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 22/5/2017.
 */

public class GuidePresenter implements GuideContract.Presenter {
    private final static String TAG = "GuidePresenter";
    private final GuideContract.View view;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    @SortType
    private int sortType = SortType.ByNumber;
    private int currentPosition = 0;

    public GuidePresenter(GuideContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        getEventList(true);
    }

    @Override
    public void getEventList(boolean isBegin) {
        if (isBegin){
            view.clearEventList();
            currentPosition = 0;
        }
        List<Integer> channelIdList = new ArrayList<>();
        int size = DataManager.getInstance().getChannelListSize();
        final int endposition = (currentPosition + 20) > size ? size : currentPosition + 20;
        List<ChannelList> channelList = (sortType == SortType.ByName)
                ? DataManager.getInstance().getChannelListByName().subList(currentPosition,endposition)
                : DataManager.getInstance().getChannelListByNumber().subList(currentPosition,endposition);
        for (ChannelList item : channelList){
            channelIdList.add(item.getChannelId());
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        String currentDate = formatter.format(calendar.getTime());
        calendar.add(Calendar.MINUTE,30);
        String endDate = formatter.format(calendar.getTime());


        Call<GetEventsResponse> call = BaseApiClient.getAstroService().getEvents(currentDate,endDate,channelIdList);
        call.enqueue(new Callback<GetEventsResponse>() {
            @Override
            public void onResponse(Call<GetEventsResponse> call, Response<GetEventsResponse> response) {
                currentPosition = endposition;
                GetEventsResponse getEventsResponse = response.body();
                Log.d(TAG,"complete get event list");
                if (getEventsResponse != null){
                    if (getEventsResponse.isSuccess()){
                        Log.d(TAG,"get event list success");
                        //notify ui
                        view.addEventList(getEventsResponse.getGetevent());
                    }else{
                        Log.d(TAG,"get event list fail");
                        view.displayErrorToast(getEventsResponse.getResponseMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetEventsResponse> call, Throwable t) {
                t.printStackTrace();
                view.displayErrorToast(t.getMessage());

            }
        });
    }

    @Override
    public void setSortType(@SortType int sortType) {
        this.sortType = sortType;
        getEventList(true);
    }
}
