package com.example.elixi.rssnews.model;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */

public class ItemRepository {
    private final itemDao itemDao ;

    @Inject
    public ItemRepository(itemDao rssDao){
        this.itemDao = rssDao;
    }

    /////////////////////////////////////////////////////
    // get db
    /////////////////////////////////////////////////////
    public LiveData<List<Item>> getWalla(){
        return itemDao.getallWalla();
    }
    public LiveData<List<Item>> getYnet(){
        return itemDao.getallYnet();
    }
    public LiveData<List<Item>> getIsraelHayom(){
        return itemDao.getallIsraelHayom();
    }
    public LiveData<List<Item>> getOne(){
        return itemDao.getallOne();
    }

    /////////////////////////////////////////////////////
    // isEmpty
    /////////////////////////////////////////////////////
    public boolean CheckDBWalla(){
        if(itemDao.CheckDBWalla().isEmpty())return true;
        return false;
    }
    public boolean CheckDBYnet(){
        if(itemDao.CheckDBYnet().isEmpty())return true;
        return false;
    }
    public boolean  CheckDBIsraelHayom(){
        if(itemDao.CheckDBIsraelHayom().isEmpty())return true;
        return false;
    }

    public boolean CheckDBOne(){
        if(itemDao.CheckDBOne().isEmpty())return true;
        return false;
    }

   /////////////////////////////////////////////////////
   // insert & delete
   /////////////////////////////////////////////////////
    public void insertOne(ArrayList<Item> item ){
        itemDao.DeleteOne();
        itemDao.insertAll(item.toArray(new Item[item.size()]));
    }
    public void insertIsraelHayom(ArrayList<Item> item ) {
        itemDao.DeleteIsraelHayom();
        itemDao.insertAll(item.toArray(new Item[item.size()]));
    }

    public void insertYnet(ArrayList<Item> item ){
        itemDao.DeletelYnet();
        itemDao.insertAll(item.toArray(new Item[item.size()]));
    }
    public void insertWalla(ArrayList<Item> item ){
        itemDao.DeleteWalla();
        itemDao.insertAll(item.toArray(new Item[item.size()]));
    }

    public com.example.elixi.rssnews.model.itemDao getItemDao() {
        return itemDao;
    }

    public void deleteListItem(){
        itemDao.deleteAll();
    }

}
