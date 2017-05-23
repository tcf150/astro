package com.zachary.astro.ui.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.zachary.astro.R;
import com.zachary.astro.base.BaseFragment;
import com.zachary.astro.model.ChannelList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22/5/2017.
 */

public class MainFragment extends BaseFragment implements MainContract.View,ChannelAdapter.OnFavouriteClickListener{
    private MainContract.Presenter presenter;

    private RecyclerView rvChannel;
    private ChannelAdapter adapter;

    private CallbackManager callbackManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);

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
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void displayChannel(List<ChannelList> channelList) {
        adapter.replaceData(channelList);
    }

    @Override
    public void displayLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.dialog_login_title);
        builder.setMessage(R.string.dialog_login_desc);
        builder.setNeutralButton(R.string.dialog_login_later, null);
        builder.setNegativeButton(R.string.dialog_login_facebook, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.facebookLogin(MainFragment.this,callbackManager);
            }
        });
        builder.setPositiveButton(R.string.dialog_login_google, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.googleLogin();
            }
        });
        builder.create().show();
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
