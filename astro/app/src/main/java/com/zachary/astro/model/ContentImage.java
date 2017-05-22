package com.zachary.astro.model;

import com.zachary.astro.base.BaseModel;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class ContentImage extends BaseModel {
    private String imageURL;
    private String imageRole;
    private String imageCaption;
    private String imageCaptionLanguage;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageRole() {
        return imageRole;
    }

    public void setImageRole(String imageRole) {
        this.imageRole = imageRole;
    }

    public String getImageCaption() {
        return imageCaption;
    }

    public void setImageCaption(String imageCaption) {
        this.imageCaption = imageCaption;
    }

    public String getImageCaptionLanguage() {
        return imageCaptionLanguage;
    }

    public void setImageCaptionLanguage(String imageCaptionLanguage) {
        this.imageCaptionLanguage = imageCaptionLanguage;
    }
}
