package com.example.elixi.rssnews.dependencyinjection;

import android.app.Application;

import com.example.elixi.rssnews.Fragment.IsraelHayomFragmen;
import com.example.elixi.rssnews.Fragment.OneFragment;
import com.example.elixi.rssnews.Fragment.WallaFragment;
import com.example.elixi.rssnews.MVVMApplication;
import com.example.elixi.rssnews.MainActivity;
import com.example.elixi.rssnews.Fragment.YnetFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    void inject(OneFragment oneFragmen);

    void inject(IsraelHayomFragmen israelHayomFragmen);

    void inject(WallaFragment wallaFragment);

    void inject(YnetFragment ynetFragment);

    void inject(MVVMApplication mvvmApplication);

    void inject(MainActivity mainActivity);

    Application application();
}
