package com.zachary.astro.ui.common;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tongcheefei on 22/05/2017.
 */

public abstract class OnRecyclerViewScrollListener extends RecyclerView.OnScrollListener{
    private boolean loading = true;
    private int previousTotal = 0;

    private LinearLayoutManager linearLayoutManager;

    public OnRecyclerViewScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if (loading){
            if (totalItemCount !=  previousTotal){
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount == visibleItemCount + firstVisibleItem)) {
            onLoadMore();
            loading = true;
        }
    }

    public abstract void onLoadMore();
}
