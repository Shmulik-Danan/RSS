package com.example.elixi.rssnews.services;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.elixi.rssnews.model.Item;
import com.example.elixi.rssnews.model.Rss;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class RetrofitRepository {
    private APIService apiService;
    ArrayList<Item> data;

    //Define Listener to indicate about new data is coming
    SyncDataInteractor interactor;


    @Inject
    public RetrofitRepository(APIService apiService, Application application) {
        this.apiService = apiService;
    }

    public void get(String url) {
        // final MutableLiveData<ArrayList<Item>> data1 = new MutableLiveData<>();

        apiService.get(url).enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(@NonNull Call<Rss> call, @NonNull Response<Rss> response) {


                if (response != null && data != response.body().getChannel().getItem()) {
                    data = response.body().getChannel().getItem();

                    //publish that new data is available
                    interactor.onSyncData(response.body().getChannel().getItem());
                    Log.d("RetrofitRepository", "onResponse: " + response.body().getChannel().getItem().toString());

                }

            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {

                interactor.onFailed(null);

                Log.d("RetrofitRepository", "onFailure: " + t.getMessage());

            }
        });
    }

    public void registerInteractot(SyncDataInteractor networkInteractor) {
        this.interactor = networkInteractor;
    }
}
