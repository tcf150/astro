package com.zachary.astro.ui.Guide;

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
import com.zachary.astro.model.Events;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22/5/2017.
 */

public class GuideFragment extends BaseFragment implements GuideContract.View {
    private GuideContract.Presenter presenter;

    private RecyclerView rvEvent;
    private GuideAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_guide,container,false);

        rvEvent = getViewByView(rootView,R.id.rvEvent);

        //setup layout manager for recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvEvent.setLayoutManager(linearLayoutManager);

        //add divider for recyclerview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        rvEvent.addItemDecoration(dividerItemDecoration);

        adapter = new GuideAdapter(new ArrayList<Events>(0));
        rvEvent.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(GuideContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayErrorToast(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearEventList() {
        adapter.clearList();
    }

    @Override
    public void addEventList(List<Events> eventsList) {
        adapter.addList(eventsList);
    }
}
