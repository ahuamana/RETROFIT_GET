package com.paparazziteam.retrofitget.interfaces;

import com.paparazziteam.retrofitget.model.ModelPosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostsInterface {

    //this interface will get all data from Webservices
    @GET("posts")
    Call<List<ModelPosts>> getDataAPI();
}
