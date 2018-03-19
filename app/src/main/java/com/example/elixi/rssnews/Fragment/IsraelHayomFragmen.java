package com.example.elixi.rssnews.Fragment;
//IsraelHayomFragmen

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elixi.rssnews.MVVMApplication;
import com.example.elixi.rssnews.MainActivity;
import com.example.elixi.rssnews.MyAdapter;
import com.example.elixi.rssnews.R;
import com.example.elixi.rssnews.viewmodel.IsraelHayomFragmentViewModel;
import com.example.elixi.rssnews.model.Item;

import java.util.List;

import javax.inject.Inject;


public class IsraelHayomFragmen extends LifecycleFragment {
    private static final String TAG = "IsraelHayomFragmen";
    private SwipeRefreshLayout swipeContainer;
    Toolbar toolbar;

    List<Item> itemList;

    RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter adapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    IsraelHayomFragmentViewModel israelHayomFragmentViewModel;


    public IsraelHayomFragmen() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment, container, false);

        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        recyclerView = (RecyclerView) v.findViewById(R.id.rss_feed_list);
        //SetTitle
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("IsraelHayom");


        ((MVVMApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);

        //Set up and subscribe (observe) to the ViewModel
        israelHayomFragmentViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(IsraelHayomFragmentViewModel.class);

        //Set LayoutManager for setAdapter
        mLayoutManager = new LinearLayoutManager(getActivity().getApplication());
        recyclerView.setLayoutManager(mLayoutManager);


        subscribe();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                israelHayomFragmentViewModel.Update();
                swipeContainer.setRefreshing(false);

            }
        });
        return v;
    }

    //observe Last list from DB --Will return  List<Item> items
    private void subscribe() {
        israelHayomFragmentViewModel.getIsraelHayomObservable().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                //For the first time->updata DB
                if (items.toString() == "[]") {
                    Log.d(TAG, "onChanged: getIsraelHayomObservable = '[]' ");
                    israelHayomFragmentViewModel.Update();
                } else save(items);
                Log.d(TAG, "onChanged: getIsraelHayomObservable = " + items.toString());
            }
        });


    }

    //Save and activate setAdapter function
    private void save(List<Item> items) {
        this.itemList = items;
        Log.d(TAG, "save: items = " + items);
        setAdapter();
    }

    //Set last List inside the adapter
    void setAdapter() {
        adapter = new MyAdapter(getActivity().getApplication(), getActivity().getSupportFragmentManager());
        adapter.setList(itemList);
        recyclerView.setAdapter(adapter);
    }


    public interface OnFragmentInteractionListener {
    }
}
