package com.example.elixi.rssnews.services;

import java.util.ArrayList;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */

public interface SyncDataInteractor <T>{

    public void onSyncData(ArrayList<T> data);//object reference
    public void onFailed(T error);

    public void onSyncData(Object reference);//object referenc

}