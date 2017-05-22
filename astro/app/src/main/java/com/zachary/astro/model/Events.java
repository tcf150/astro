package com.zachary.astro.model;

import com.zachary.astro.base.BaseModel;

import java.util.Date;
import java.util.List;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public class Events extends BaseModel {
    private String eventId;
    private int channelId;
    private String channelStbNumber;
    private String channelHD;
    private String channelTitle;
    private String certification;
    private Date displayDateTimeUtc;
    private Date getDisplayDateTime; //YYYY-mm-dd HH:mm:sssz
    private String displayDuration; //HH : mm
    private String siTracfficKey;
    private String programmeId;
    private String episodeId;
    private String shortSynopsis;
    private String longSynopsis;
    private String genre;
    private String subGenre;
    private boolean live;
    private boolean premier;
    private boolean ottBlackout;
    private boolean highlight;
    private int contentId;
    private String epgEventImage;
    private List<ContentImage> contentImage;
    private String groupKey;
    private List<Vernacular> vernacularData;


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelStbNumber() {
        return channelStbNumber;
    }

    public void setChannelStbNumber(String channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public String getChannelHD() {
        return channelHD;
    }

    public void setChannelHD(String channelHD) {
        this.channelHD = channelHD;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Date getDisplayDateTimeUtc() {
        return displayDateTimeUtc;
    }

    public void setDisplayDateTimeUtc(Date displayDateTimeUtc) {
        this.displayDateTimeUtc = displayDateTimeUtc;
    }

    public Date getGetDisplayDateTime() {
        return getDisplayDateTime;
    }

    public void setGetDisplayDateTime(Date getDisplayDateTime) {
        this.getDisplayDateTime = getDisplayDateTime;
    }

    public String getDisplayDuration() {
        return displayDuration;
    }

    public void setDisplayDuration(String displayDuration) {
        this.displayDuration = displayDuration;
    }

    public String getSiTracfficKey() {
        return siTracfficKey;
    }

    public void setSiTracfficKey(String siTracfficKey) {
        this.siTracfficKey = siTracfficKey;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public String getShortSynopsis() {
        return shortSynopsis;
    }

    public void setShortSynopsis(String shortSynopsis) {
        this.shortSynopsis = shortSynopsis;
    }

    public String getLongSynopsis() {
        return longSynopsis;
    }

    public void setLongSynopsis(String longSynopsis) {
        this.longSynopsis = longSynopsis;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isPremier() {
        return premier;
    }

    public void setPremier(boolean premier) {
        this.premier = premier;
    }

    public boolean isOttBlackout() {
        return ottBlackout;
    }

    public void setOttBlackout(boolean ottBlackout) {
        this.ottBlackout = ottBlackout;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getEpgEventImage() {
        return epgEventImage;
    }

    public void setEpgEventImage(String epgEventImage) {
        this.epgEventImage = epgEventImage;
    }

    public List<ContentImage> getContentImage() {
        return contentImage;
    }

    public void setContentImage(List<ContentImage> contentImage) {
        this.contentImage = contentImage;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public List<Vernacular> getVernacularData() {
        return vernacularData;
    }

    public void setVernacularData(List<Vernacular> vernacularData) {
        this.vernacularData = vernacularData;
    }
}
