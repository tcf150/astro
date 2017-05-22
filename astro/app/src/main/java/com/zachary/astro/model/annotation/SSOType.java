package com.zachary.astro.model.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tongcheefei on 22/05/2017.
 */

@IntDef({SSOType.GOOGLE, SSOType.FACEBOOK})
@Retention(RetentionPolicy.SOURCE)
public @interface SSOType {
    int GOOGLE = 0;
    int FACEBOOK = 1;
}
