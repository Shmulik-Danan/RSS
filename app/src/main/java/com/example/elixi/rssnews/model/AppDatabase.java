package com.example.elixi.rssnews.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


/**
 * Created by Shmulik on 18 מרץ 2018.
 */

@Database(entities = {Item.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase {
    //private static AppDatabase singleton;
    public static final String DATABASE_NAME = "ChannelDb.db";

    public abstract itemDao itemDao();


    }


