package com.zachary.astro.base;

/**
 * Created by user on 10/5/2017.
 */

public class BaseResponse {
    private String responseMessage;
    private String responseCode;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public boolean isSuccess(){
        return responseCode.equals("200");
    }
}
