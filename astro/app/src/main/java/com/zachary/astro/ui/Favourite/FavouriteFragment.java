package com.zachary.astro.ui.Favourite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zachary.astro.R;
import com.zachary.astro.base.BaseFragment;
import com.zachary.astro.model.ChannelList;
import com.zachary.astro.ui.Main.ChannelAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 23/5/2017.
 */

public class FavouriteFragment extends BaseFragment implements FavouriteContract.View, ChannelAdapter.OnFavouriteClickListener{
    private FavouriteContract.Presenter presenter;

    private RecyclerView rvChannel;
    private ChannelAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourite,container,false);

        rvChannel = getViewByView(rootView,R.id.rvChannel);

        //setup layout manager for recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvChannel.setLayoutManager(linearLayoutManager);

        //add divider for recyclerview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        rvChannel.addItemDecoration(dividerItemDecoration);

        adapter = new ChannelAdapter(new ArrayList<ChannelList>(0));
        adapter.setOnFavouriteClickListener(this);
        rvChannel.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void setPresenter(FavouriteContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.start();
    }

    @Override
    public void displayChannel(List<ChannelList> channelList) {
        adapter.replaceData(channelList);
    }


    @Override
    public void displayErrorToast(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFavouriteClick(ChannelList channel) {
        presenter.favouriteChannel(channel);
    }
}
