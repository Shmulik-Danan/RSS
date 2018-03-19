package com.example.elixi.rssnews.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.util.ArrayMap;

import com.example.elixi.rssnews.dependencyinjection.ViewModelSubComponent;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */
@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public CustomViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
        creators = new ArrayMap<>();

        // View models cannot be injected directly because they won't be bound to the owner's view model scope.

        //creators.put(MainActivityViewModel.class, () -> viewModelSubComponent.MAIN_ACTIVITY());
        creators.put(WallaFragmentViewModel.class, () -> viewModelSubComponent.WALLA_FRAGMENT_VIEW_MODEL());
        creators.put(YnetFragmentViewModel.class, () -> viewModelSubComponent.YNET_FRAGMENT_VIEW_MODEL());
        creators.put(IsraelHayomFragmentViewModel.class, () -> viewModelSubComponent.ISRAEL_HAYOM_FRAGMENT_VIEW_MODEL());
        creators.put(OneFragmentViewModel.class, () -> viewModelSubComponent.ONE_FRAGMENT_VIEW_MODEL());


    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
