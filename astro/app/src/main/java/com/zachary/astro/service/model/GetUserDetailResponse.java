package com.zachary.astro.service.model;

import com.zachary.astro.base.BaseResponse;
import com.zachary.astro.model.User;

/**
 * Created by user on 23/5/2017.
 */

public class GetUserDetailResponse extends BaseResponse {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
