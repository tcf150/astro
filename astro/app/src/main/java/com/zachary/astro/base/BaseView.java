package com.zachary.astro.base;

/**
 * Created by user on 10/5/2017.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
