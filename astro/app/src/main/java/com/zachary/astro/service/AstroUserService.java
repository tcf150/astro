package com.zachary.astro.service;

import com.zachary.astro.service.model.AddFavouriteResponse;
import com.zachary.astro.service.model.CreateUserResponse;
import com.zachary.astro.service.model.GetFavouriteListResponse;
import com.zachary.astro.service.model.GetUserDetailResponse;
import com.zachary.astro.service.model.RemoveFavouriteResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by user on 23/5/2017.
 */

public interface AstroUserService {
    @GET("getUserDetail.php")
    Call<GetUserDetailResponse> getUserDetail(@Query("userId") int userId);

    @FormUrlEncoded
    @POST("createUser.php")
    Call<CreateUserResponse> createUser(@Field("socialId") String socialId, @Field("ssoType") int ssoType);

    @GET("getFavouriteList.php")
    Call<GetFavouriteListResponse> getFavouriteList(@Query("userId") int userId);

    @FormUrlEncoded
    @POST("addFavourite.php")
    Call<AddFavouriteResponse> addFavourite(@Field("userId") int userId, @Field("channelId") int channelId, @Field("channelTitle") String channelTitle, @Field("channelStbNumber") int channelStbNumber);

    @FormUrlEncoded
    @POST("removeFavourite.php")
    Call<RemoveFavouriteResponse> removeFavourite(@Field("favouriteId") int favouriteId);
}
