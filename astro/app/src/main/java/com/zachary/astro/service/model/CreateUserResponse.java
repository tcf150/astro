package com.zachary.astro.service.model;

import com.zachary.astro.base.BaseResponse;

/**
 * Created by user on 23/5/2017.
 */

public class CreateUserResponse extends BaseResponse {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
