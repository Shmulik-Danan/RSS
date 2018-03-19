

package com.example.elixi.rssnews.dependencyinjection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.example.elixi.rssnews.viewmodel.CustomViewModelFactory;
import com.example.elixi.rssnews.model.AppDatabase;
import com.example.elixi.rssnews.model.ItemRepository;
import com.example.elixi.rssnews.model.itemDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(subcomponents = ViewModelSubComponent.class)
public class RoomModule {

    private final AppDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(
                application,
                AppDatabase.class,
                "ItemDb.db"
        ).build();
    }

    @Provides
    @Singleton
    ItemRepository provideListItemRepository(itemDao itemDao){
        return new ItemRepository(itemDao);
    }

    @Provides
    @Singleton
    itemDao provideListItemDao(AppDatabase database){
        return database.itemDao();
    }

    @Provides
    @Singleton
    AppDatabase provideListItemDatabase(Application application){
        return database;
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new CustomViewModelFactory(viewModelSubComponent.build());

    }
}
