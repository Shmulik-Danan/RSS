package com.example.elixi.rssnews;

import android.app.Application;
import android.util.Log;

import com.example.elixi.rssnews.dependencyinjection.ApplicationComponent;
import com.example.elixi.rssnews.dependencyinjection.ApplicationModule;
import com.example.elixi.rssnews.dependencyinjection.DaggerApplicationComponent;
import com.example.elixi.rssnews.dependencyinjection.RetrofitModule;
import com.example.elixi.rssnews.dependencyinjection.RoomModule;

import static android.content.ContentValues.TAG;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */

public class MVVMApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .retrofitModule(new RetrofitModule())
                .build();
        Log.e(TAG, "onCreate: MVVMApplication" );

        applicationComponent.inject(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}