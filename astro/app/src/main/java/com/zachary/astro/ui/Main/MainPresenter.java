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
import com.zachary.astro.model.User;
import com.zachary.astro.model.annotation.SSOType;
import com.zachary.astro.model.annotation.SortType;
import com.zachary.astro.service.model.AddFavouriteResponse;
import com.zachary.astro.service.model.CreateUserResponse;
import com.zachary.astro.service.model.GetChannelListResponse;
import com.zachary.astro.service.model.GetFavouriteListResponse;
import com.zachary.astro.service.model.GetUserDetailResponse;
import com.zachary.astro.service.model.RemoveFavouriteResponse;

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

    private int channelSortType = SortType.ByNumber;

    public MainPresenter(MainContract.View view){
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        if (UserManager.getInstance().hasUserId()){
            getChannelList();
        }else {
            String cacheUserId = UserManager.getInstance().getUserIdCache();
            if (cacheUserId != null && cacheUserId.length() > 0) {
                view.showLoading(true);
                Call<GetUserDetailResponse> call = BaseApiClient.getAstroUserService().getUserDetail(Integer.valueOf(cacheUserId));
                call.enqueue(new Callback<GetUserDetailResponse>() {
                    @Override
                    public void onResponse(Call<GetUserDetailResponse> call, Response<GetUserDetailResponse> response) {
                        GetUserDetailResponse getUserDetailResponse = response.body();
                        User user = getUserDetailResponse.getUser();
                        if (user != null) {
                            UserManager.getInstance().setUser(user);
                            getFavouriteList();
                        } else {
                            UserManager.getInstance().setUserIdCache("");
                            getChannelList();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetUserDetailResponse> call, Throwable t) {
                        UserManager.getInstance().setUserIdCache("");
                    }
                });
            } else {
                getChannelList();
            }
        }
    }

    private void getFavouriteList(){
        Call<GetFavouriteListResponse> call = BaseApiClient.getAstroUserService().getFavouriteList(Integer.valueOf(UserManager.getInstance().getUserId()));
        call.enqueue(new Callback<GetFavouriteListResponse>() {
            @Override
            public void onResponse(Call<GetFavouriteListResponse> call, Response<GetFavouriteListResponse> response) {
                GetFavouriteListResponse getFavouriteListResponse = response.body();
                UserManager.getInstance().addFavouriteChannelList(getFavouriteListResponse.getFavouriteList());
                getChannelList();
            }

            @Override
            public void onFailure(Call<GetFavouriteListResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getChannelList(){
        if (DataManager.getInstance().getChannelListSize() == 0){
            view.showLoading(true);
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
                            switch (channelSortType){
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
                    view.showLoading(false);
                }

                @Override
                public void onFailure(Call<GetChannelListResponse> call, Throwable t) {
                    t.printStackTrace();
                    view.displayErrorToast(t.getMessage());
                    view.showLoading(false);

                }
            });
        }else{
            switch (channelSortType){
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
    public void updateChannelSort(int sortType) {
        channelSortType = sortType;
        getChannelList();
    }

    @Override
    public void onFavouriteMenuClick() {
        if (UserManager.getInstance().hasUserId()){
            view.gotoFavourite();
        }else{
            view.displayLoginDialog();
        }
    }

    @Override
    public void favouriteChannel(final ChannelList channel) {
        if (UserManager.getInstance().hasUserId()){
            //logined
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
                                getChannelList();
                            }else{
                                UserManager.getInstance().removeFavouriteChannel(favouriteChannel.getFavouriteId());
                            }
                        }

                        @Override
                        public void onFailure(Call<RemoveFavouriteResponse> call, Throwable t) {
                            DataManager.getInstance().updateChannelList(channel.getChannelId(),true);
                            view.displayErrorToast(t.getMessage());
                            getChannelList();
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
                            getChannelList();
                        }else{
                            ChannelList item = new ChannelList();
                            item.setFavouriteId(addFavouriteResponse.getFavouriteId());
                            item.setChannelId(channel.getChannelId());
                            item.setChannelTitle(channel.getChannelTitle());
                            item.setChannelStbNumber(channel.getChannelStbNumber());
                            item.setFavourite(true);
                            UserManager.getInstance().addFavouriteChannel(item);
                        }
                    }

                    @Override
                    public void onFailure(Call<AddFavouriteResponse> call, Throwable t) {
                        DataManager.getInstance().updateChannelList(channel.getChannelId(),false);
                        view.displayErrorToast(t.getMessage());
                        getChannelList();
                    }
                });
            }
            getChannelList();
        }else{
            //no user account
            view.displayLoginDialog();
        }
    }

    @Override
    public void facebookLogin(Fragment fragment,CallbackManager callbackManager) {
        view.showLoading(true);
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
                view.showLoading(false);
            }

            @Override
            public void onError(FacebookException error) {
                view.showLoading(false);
            }
        });
    }

    @Override
    public void googleLogin(final String socialId) {
        Call<CreateUserResponse> call = BaseApiClient.getAstroUserService().createUser(socialId,SSOType.GOOGLE);
        call.enqueue(new Callback<CreateUserResponse>() {
            @Override
            public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                CreateUserResponse createUserResponse = response.body();
                if (createUserResponse.isSuccess() || createUserResponse.getResponseCode().equals("201")){
                    User user = new User();
                    user.setUserId(String.valueOf(createUserResponse.getUserId()));
                    user.setSocialId(socialId);
                    user.setSsoType(SSOType.GOOGLE);
                    UserManager.getInstance().setUser(user);
                    UserManager.getInstance().setUserIdCache(String.valueOf(createUserResponse.getUserId()));
                    getFavouriteList();
                }else{
                    view.displayErrorToast(createUserResponse.getResponseMessage());
                }
                view.showLoading(false);
            }

            @Override
            public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                view.showLoading(false);
            }
        });
    }

    @Override
    public void logout() {
        UserManager.getInstance().clearCache();
        DataManager.getInstance().clearChannelListFavourite();
        getChannelList();
    }

    private void getFacebookGraph(AccessToken accessToken){
        Bundle paramter = new Bundle();
        paramter.putString("fields","id,email,name");
        GraphRequest request = new GraphRequest(accessToken,"me",paramter, HttpMethod.GET,
                new GraphRequest.Callback(){

                    @Override
                    public void onCompleted(GraphResponse response) {
                        try{
                            final String socialId = response.getJSONObject().get("id").toString();
                            Call<CreateUserResponse> call = BaseApiClient.getAstroUserService().createUser(socialId,SSOType.FACEBOOK);
                            call.enqueue(new Callback<CreateUserResponse>() {
                                @Override
                                public void onResponse(Call<CreateUserResponse> call, Response<CreateUserResponse> response) {
                                    CreateUserResponse createUserResponse = response.body();
                                    if (createUserResponse.isSuccess() || createUserResponse.getResponseCode().equals("201")){
                                        User user = new User();
                                        user.setUserId(String.valueOf(createUserResponse.getUserId()));
                                        user.setSocialId(socialId);
                                        user.setSsoType(SSOType.FACEBOOK);
                                        UserManager.getInstance().setUser(user);
                                        UserManager.getInstance().setUserIdCache(String.valueOf(createUserResponse.getUserId()));
                                        getFavouriteList();
                                    }else{
                                        view.displayErrorToast(createUserResponse.getResponseMessage());
                                    }
                                    view.showLoading(false);
                                }

                                @Override
                                public void onFailure(Call<CreateUserResponse> call, Throwable t) {
                                    view.showLoading(false);
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
        request.executeAsync();
    }
}
