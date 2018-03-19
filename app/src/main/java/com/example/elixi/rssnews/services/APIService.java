package com.example.elixi.rssnews.services;

import com.example.elixi.rssnews.model.Rss;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIService {
    String HTTPS_API_RSS_URL = "https://newsapi.org/v2/";

    @GET
    Call<Rss> get(@Url String url);

    }
