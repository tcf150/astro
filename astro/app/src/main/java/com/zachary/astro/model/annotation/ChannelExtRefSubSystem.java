package com.zachary.astro.model.annotation;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tongcheefei on 22/05/2017.
 */

@StringDef({
        ChannelExtRefSubSystem.NEG_600X370,
        ChannelExtRefSubSystem.NEG_357X220,
        ChannelExtRefSubSystem.NEG_300X185,
        ChannelExtRefSubSystem.NEG_160X99,
        ChannelExtRefSubSystem.NEG_120X74,
        ChannelExtRefSubSystem.NEG_100X62,
        ChannelExtRefSubSystem.NEG_60X37,
        ChannelExtRefSubSystem.NEG_51X31,

        ChannelExtRefSubSystem.POS_600X370,
        ChannelExtRefSubSystem.POS_357X220,
        ChannelExtRefSubSystem.POS_300X185,
        ChannelExtRefSubSystem.POS_160X99,
        ChannelExtRefSubSystem.POS_120X74,
        ChannelExtRefSubSystem.POS_100X62,
        ChannelExtRefSubSystem.POS_60X37,
        ChannelExtRefSubSystem.POS_51X31,
})
@Retention(RetentionPolicy.SOURCE)
public @interface ChannelExtRefSubSystem {
    String NEG_600X370 = "Neg_600x370";
    String NEG_357X220 = "Neg_357x220";
    String NEG_300X185 = "Neg_300x185";
    String NEG_160X99 = "Neg_160x99";
    String NEG_120X74 = "Neg_120x74";
    String NEG_100X62 = "Neg_100x62";
    String NEG_60X37 = "Neg_60x37";
    String NEG_51X31 = "Neg_51x31";

    String POS_600X370 = "Pos_600x370";
    String POS_357X220 = "Pos_357x220";
    String POS_300X185 = "Pos_300x185";
    String POS_160X99 = "Pos_160x99";
    String POS_120X74 = "Pos_120x74";
    String POS_100X62 = "Pos_100x62";
    String POS_60X37 = "Pos_60x37";
    String POS_51X31 = "Pos_51x31";
}
