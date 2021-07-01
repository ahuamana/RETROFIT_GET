package com.paparazziteam.retrofitget.interfaces;

import com.paparazziteam.retrofitget.model.News;
import com.paparazziteam.retrofitget.model.NewsParent;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsInterface {

    @GET("v1/news?access_key=2a6960abe7dc05eaf9f2ca28738cab10&sources=cnn,bbc")
    Call<NewsParent> getJsonData();
}
