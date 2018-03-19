package com.example.elixi.rssnews.dependencyinjection;

import android.app.Application;

import com.example.elixi.rssnews.services.APIService;
import com.example.elixi.rssnews.services.RetrofitRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Shmulik on 18 מרץ 2018.
 */

@Module
public class RetrofitModule {
    private APIService service ;


    public RetrofitModule() {

        service = new Retrofit.Builder()
               .baseUrl(APIService.HTTPS_API_RSS_URL)
                //.client(client)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
                .build()
                .create(APIService.class);

    }


    @Provides
    @Singleton
    RetrofitRepository provideProjectRepository(APIService gitHubService, Application application) {
        return new RetrofitRepository(gitHubService,application);
    }


    @Singleton
    @Provides
    APIService provideGithubService(Application application) {
        return service;
    }

/*
    @Provides
    @Singleton
    ViewModel provideViewModel(RetrofitRepository repository){
        return new RetrofitViewModel(repository);
    }*/

}
