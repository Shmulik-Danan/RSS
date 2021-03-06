package com.example.elixi.rssnews.viewmodel;
//IsraelHayomFragmentViewModel

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.elixi.rssnews.services.RetrofitRepository;
import com.example.elixi.rssnews.services.SyncDataInteractor;
import com.example.elixi.rssnews.model.Item;
import com.example.elixi.rssnews.model.ItemRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */
@Singleton
public class IsraelHayomFragmentViewModel extends ViewModel implements SyncDataInteractor {
    private static final String TAG = "IsraelHayomFraViewModel";
    private ItemRepository repository;
    private RetrofitRepository retrofitRepository;

    @Inject
    public IsraelHayomFragmentViewModel(ItemRepository repository, RetrofitRepository retrofitRepository,
                                        @NonNull Application application) {
        this.repository = repository;
        this.retrofitRepository = retrofitRepository;

    }
    //Update//asks new data from Service
    public void Update() {
        this.retrofitRepository.registerInteractot(this);
        this.retrofitRepository.get("http://www.israelhayom.co.il/rss.xml");
    }
    /**
     * Attach our LiveData to the Database
     */
    public LiveData<List<Item>> getIsraelHayomObservable() {
        return repository.getIsraelHayom();
    }


    //Indicate that List ready to load (will be call from our retrofitRepository)
    @Override
    public void onSyncData(ArrayList data) {
        Log.d(TAG, "onSyncData: "+data);
        Insert(data);
    }

    @Override
    public void onFailed(Object error) {

    }


    //Insert list to DB
    public boolean Insert(ArrayList<Item> item) {
        InsertTask addTask = new InsertTask();
        addTask.execute(item);
        return true;
    }
    private class InsertTask extends AsyncTask<ArrayList<Item>, Void, Void> {

        @Override
        protected Void doInBackground(ArrayList<Item>[] arrayLists) {

                repository.insertIsraelHayom(arrayLists[0]);


            return null;
        }
    }
}

