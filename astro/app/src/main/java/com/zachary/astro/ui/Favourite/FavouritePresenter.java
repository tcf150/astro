package com.zachary.astro.ui.Favourite;

import android.util.Log;

import com.zachary.astro.base.BaseApiClient;
import com.zachary.astro.data.DataManager;
import com.zachary.astro.data.UserManager;
import com.zachary.astro.model.ChannelList;
import com.zachary.astro.model.annotation.SortType;
import com.zachary.astro.service.model.AddFavouriteResponse;
import com.zachary.astro.service.model.GetChannelListResponse;
import com.zachary.astro.service.model.RemoveFavouriteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 23/5/2017.
 */

public class FavouritePresenter implements FavouriteContract.Presenter {
    private final static String TAG = "MainPresenter";
    private final FavouriteContract.View view;

    private int channelSortType = SortType.ByNumber;

    public FavouritePresenter(FavouriteContract.View view){
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        getChannelList();
    }


    @Override
    public void favouriteChannel(final ChannelList channel) {
        if (channel.isFavourite()){
            DataManager.getInstance().updateChannelList(channel.getChannelId(),false);

            final ChannelList favouriteChannel = UserManager.getInstance().getFavouriteById(channel.getChannelId());
            if (favouriteChannel != null) {
                Call<RemoveFavouriteResponse> call = BaseApiClient.getAstroUserService().removeFavourite(favouriteChannel.getFavouriteId());
                call.enqueue(new Callback<RemoveFavouriteResponse>() {
                    @Override
                    public void onResponse(Call<RemoveFavouriteResponse> call, Response<RemoveFavouriteResponse> response) {
                        RemoveFavouriteResponse removeFavouriteResponse = response.body();
                        if (!removeFavouriteResponse.isSuccess()){
                            DataManager.getInstance().updateChannelList(channel.getChannelId(),true);
                            view.displayErrorToast(removeFavouriteResponse.getResponseMessage());
                        }else{
                            UserManager.getInstance().removeFavouriteChannel(favouriteChannel.getFavouriteId());
                            getChannelList();
                        }
                    }

                    @Override
                    public void onFailure(Call<RemoveFavouriteResponse> call, Throwable t) {
                        DataManager.getInstance().updateChannelList(channel.getChannelId(),true);
                        view.displayErrorToast(t.getMessage());
                    }
                });
            }
        }else{
            DataManager.getInstance().updateChannelList(channel.getChannelId(),true);

            Call<AddFavouriteResponse> call = BaseApiClient.getAstroUserService().addFavourite(Integer.valueOf(UserManager.getInstance().getUserId()),channel.getChannelId(), channel.getChannelTitle(),channel.getChannelStbNumber());
            call.enqueue(new Callback<AddFavouriteResponse>() {
                @Override
                public void onResponse(Call<AddFavouriteResponse> call, Response<AddFavouriteResponse> response) {
                    AddFavouriteResponse addFavouriteResponse = response.body();
                    if (!addFavouriteResponse.isSuccess()){
                        DataManager.getInstance().updateChannelList(channel.getChannelId(),false);
                        view.displayErrorToast(addFavouriteResponse.getResponseMessage());
                    }else{
                        ChannelList item = new ChannelList();
                        item.setFavouriteId(addFavouriteResponse.getFavouriteId());
                        item.setChannelId(channel.getChannelId());
                        item.setChannelTitle(channel.getChannelTitle());
                        item.setChannelStbNumber(channel.getChannelStbNumber());
                        item.setFavourite(true);
                        UserManager.getInstance().addFavouriteChannel(item);
                        getChannelList();
                    }
                }

                @Override
                public void onFailure(Call<AddFavouriteResponse> call, Throwable t) {
                    DataManager.getInstance().updateChannelList(channel.getChannelId(),false);
                    view.displayErrorToast(t.getMessage());
                }
            });
        }
        getChannelList();
    }

    @Override
    public void updateChannelSort(@SortType int sortType) {
        channelSortType = sortType;
        getChannelList();
    }

    private void getChannelList(){
        switch (channelSortType){
            default:
            case SortType.ByNumber:
                view.displayChannel(UserManager.getInstance().getFavouriteListByNumber());
                break;
            case SortType.ByName:
                view.displayChannel(UserManager.getInstance().getFavouriteListByName());
                break;
        }
    }
}
