package com.zachary.astro.service.model;

import com.zachary.astro.base.BaseResponse;
import com.zachary.astro.model.Events;

import java.util.List;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class GetEventsResponse extends BaseResponse {
    private List<Events> getevent;

    public List<Events> getGetevent() {
        return getevent;
    }

    public void setGetevent(List<Events> getevent) {
        this.getevent = getevent;
    }
}
