package com.example.elixi.rssnews.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */
@Dao
public interface itemDao {

    /////////////////////////////////////////////////////
    // get db
    /////////////////////////////////////////////////////
    @Query("SELECT * FROM item WHERE link LIKE '%walla%'")
    LiveData<List<Item>> getallWalla();

    @Query("SELECT * FROM item WHERE link LIKE '%ynet%'")
    LiveData<List<Item>> getallYnet();

    @Query("SELECT * FROM item WHERE link LIKE '%israelhayom%'")
    LiveData<List<Item>> getallIsraelHayom();

    @Query("SELECT * FROM item WHERE link LIKE '%one%'")
    LiveData<List<Item>> getallOne();


    /////////////////////////////////////////////////////
    // isEmpty
    /////////////////////////////////////////////////////

    @Query("SELECT * FROM item WHERE link LIKE '%walla%'")
    List<Item> CheckDBWalla();

    @Query("SELECT * FROM item WHERE link LIKE '%ynet%'")
    List<Item> CheckDBYnet();

    @Query("SELECT * FROM item WHERE link LIKE '%israelhayom%'")
    List<Item> CheckDBIsraelHayom();

    @Query("SELECT * FROM item WHERE link LIKE '%one%'")
    List<Item> CheckDBOne();

     /////////////////////////////////////////////////////
    // insert & delete
    /////////////////////////////////////////////////////

    @Query("DELETE  FROM item WHERE link LIKE '%walla%'")
    void DeleteWalla();

    @Query("DELETE  FROM item WHERE link LIKE '%ynet%'")
    void DeletelYnet();

    @Query("DELETE  FROM item WHERE link LIKE '%israelhayom%'")
    void DeleteIsraelHayom();

    @Query("DELETE  FROM item WHERE link LIKE '%one%'")
    void DeleteOne();



    @Insert
    void insertAll(Item... item);

    @Query("DELETE FROM item")
    void deleteAll();


}
