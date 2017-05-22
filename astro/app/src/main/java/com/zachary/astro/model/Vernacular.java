package com.zachary.astro.model;

import com.zachary.astro.base.BaseModel;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class Vernacular extends BaseModel {
    private String vernacularLanguage;
    private String vernacularProgrammeTitle;
    private String vernacularLongSynopsis;

    public String getVernacularLanguage() {
        return vernacularLanguage;
    }

    public void setVernacularLanguage(String vernacularLanguage) {
        this.vernacularLanguage = vernacularLanguage;
    }

    public String getVernacularProgrammeTitle() {
        return vernacularProgrammeTitle;
    }

    public void setVernacularProgrammeTitle(String vernacularProgrammeTitle) {
        this.vernacularProgrammeTitle = vernacularProgrammeTitle;
    }

    public String getVernacularLongSynopsis() {
        return vernacularLongSynopsis;
    }

    public void setVernacularLongSynopsis(String vernacularLongSynopsis) {
        this.vernacularLongSynopsis = vernacularLongSynopsis;
    }
}
