package com.zachary.astro.model.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by user on 22/5/2017.
 */

@IntDef({SortType.ByName, SortType.ByNumber})
@Retention(RetentionPolicy.SOURCE)
public @interface SortType {
    int ByName = 0;
    int ByNumber = 1;
}
