package com.zachary.astro.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 10/5/2017.
 */

public class BaseViewHolder<T extends BaseModel> extends RecyclerView.ViewHolder {
    protected T model;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void bindData(T model){
        this.model = model;
    }
}
