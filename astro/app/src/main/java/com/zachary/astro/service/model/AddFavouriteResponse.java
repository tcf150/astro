package com.zachary.astro.service.model;

import com.zachary.astro.base.BaseResponse;

/**
 * Created by user on 23/5/2017.
 */

public class AddFavouriteResponse extends BaseResponse {
    private int favouriteId;

    public int getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(int favouriteId) {
        this.favouriteId = favouriteId;
    }
}
