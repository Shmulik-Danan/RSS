package com.example.elixi.rssnews.dependencyinjection;


import com.example.elixi.rssnews.viewmodel.IsraelHayomFragmentViewModel;
import com.example.elixi.rssnews.viewmodel.OneFragmentViewModel;
import com.example.elixi.rssnews.viewmodel.WallaFragmentViewModel;
import com.example.elixi.rssnews.viewmodel.YnetFragmentViewModel;

import dagger.Subcomponent;


@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }


    //MainActivityViewModel MAIN_ACTIVITY();
    WallaFragmentViewModel WALLA_FRAGMENT_VIEW_MODEL();
    YnetFragmentViewModel YNET_FRAGMENT_VIEW_MODEL();
    IsraelHayomFragmentViewModel ISRAEL_HAYOM_FRAGMENT_VIEW_MODEL();
    OneFragmentViewModel ONE_FRAGMENT_VIEW_MODEL();
}
