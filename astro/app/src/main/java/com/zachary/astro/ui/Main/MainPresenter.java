package com.zachary.astro.ui.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.zachary.astro.base.BaseApiClient;
import com.zachary.astro.data.DataManager;
import com.zachary.astro.data.UserManager;
import com.zachary.astro.model.ChannelList;
import com.zachary.astro.model.annotation.SortType;
import com.zachary.astro.service.model.GetChannelListResponse;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 22/5/2017.
 */

public class MainPresenter implements MainContract.Presenter {
    private final static String TAG = "MainPresenter";
    private final MainContract.View view;

    public MainPresenter(MainContract.View view){
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        getChannelList(SortType.ByNumber);
    }

    @Override
    public void getChannelList(@SortType final int sortType){
        if (DataManager.getInstance().getChannelListSize() == 0){
            Call<GetChannelListResponse> call = BaseApiClient.getAstroService().getChannelList();
            call.enqueue(new Callback<GetChannelListResponse>() {
                @Override
                public void onResponse(Call<GetChannelListResponse> call, Response<GetChannelListResponse> response) {
                    GetChannelListResponse getChannelListResponse = response.body();
                    Log.d(TAG,"complete get channel list");
                    if (getChannelListResponse != null){
                        if (getChannelListResponse.isSuccess()){
                            Log.d(TAG,"get channel list success");
                            //notify ui
                            List<ChannelList> channelList = getChannelListResponse.getChannels();

                            for (ChannelList item : channelList){
                                if (UserManager.getInstance().isFavourited(item.getChannelId())){
                                    item.setFavourite(true);
                                }else{
                                    item.setFavourite(false);
                                }
                            }
                            DataManager.getInstance().setChannelList(channelList);
                            switch (sortType){
                                default:
                                case SortType.ByNumber:
                                    view.displayChannel(DataManager.getInstance().getChannelListByNumber());
                                    break;
                                case SortType.ByName:
                                    view.displayChannel(DataManager.getInstance().getChannelListByName());
                                    break;
                            }
                        }else{
                            Log.d(TAG,"get channel list fail");
                            view.displayErrorToast(getChannelListResponse.getResponseMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetChannelListResponse> call, Throwable t) {
                    t.printStackTrace();
                    view.displayErrorToast(t.getMessage());

                }
            });
        }else{
            switch (sortType){
                default:
                case SortType.ByNumber:
                    view.displayChannel(DataManager.getInstance().getChannelListByNumber());
                    break;
                case SortType.ByName:
                    view.displayChannel(DataManager.getInstance().getChannelListByName());
                    break;
            }
        }
    }

    @Override
    public void favouriteChannel(ChannelList channel) {

    }

    @Override
    public void facebookLogin(Fragment fragment,CallbackManager callbackManager) {
        if (AccessToken.getCurrentAccessToken() != null){
            getFacebookGraph(AccessToken.getCurrentAccessToken());
            return;
        }

        LoginManager.getInstance().logInWithReadPermissions(fragment, Arrays.asList("public_profile","email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getFacebookGraph(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void getFacebookGraph(AccessToken accessToken){
        Bundle paramter = new Bundle();
        paramter.putString("fields","id,email,name");
        GraphRequest request = new GraphRequest(accessToken,"me",paramter, HttpMethod.GET,
                new GraphRequest.Callback(){

                    @Override
                    public void onCompleted(GraphResponse response) {
//                        response.getJSONObject().get("me").toString()
//                        response.getJSONObject().has("email")
//                        response.getJSONObject().get("id").toString()
                    }
                });
        request.executeAsync();
    }
}