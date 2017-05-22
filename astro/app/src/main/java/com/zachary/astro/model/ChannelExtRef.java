package com.zachary.astro.model;

import com.zachary.astro.base.BaseModel;
import com.zachary.astro.model.annotation.ChannelExtRefSubSystem;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class ChannelExtRef extends BaseModel {
    private String system;
    @ChannelExtRefSubSystem
    private String subSystem;
    private String value;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @ChannelExtRefSubSystem
    public String getSubSystem() {
        return subSystem;
    }

    public void setSubSystem(@ChannelExtRefSubSystem String subSystem) {
        this.subSystem = subSystem;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
