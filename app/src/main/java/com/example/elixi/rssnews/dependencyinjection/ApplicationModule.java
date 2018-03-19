package com.example.elixi.rssnews.dependencyinjection;

import android.app.Application;

import com.example.elixi.rssnews.MVVMApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */

@Module
public class ApplicationModule {
    private final MVVMApplication application;
    public ApplicationModule(MVVMApplication application) {
        this.application = application;
    }

    @Provides
    MVVMApplication provideRoomDemoApplication(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }
}
