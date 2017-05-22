package com.zachary.astro.base;

import com.zachary.astro.model.Status;

/**
 * Created by user on 10/5/2017.
 */

public class BaseResponse {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
