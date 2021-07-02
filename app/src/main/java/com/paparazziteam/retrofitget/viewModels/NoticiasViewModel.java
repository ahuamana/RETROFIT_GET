package com.paparazziteam.retrofitget.viewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.paparazziteam.retrofitget.adapters.NewsAdapter;
import com.paparazziteam.retrofitget.databinding.FragmentNewsBinding;
import com.paparazziteam.retrofitget.interfaces.NewsInterface;
import com.paparazziteam.retrofitget.model.News;
import com.paparazziteam.retrofitget.model.NewsParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticiasViewModel  extends ViewModel {
    //Implement everything

    NewsAdapter mAdapter;
    NewsInterface mNewsInterface;


    public void getNewsSources(FragmentNewsBinding binding, Context context) {

        Log.e("Codigo ", "inicial");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.mediastack.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //call interface
        mNewsInterface = retrofit.create(NewsInterface.class);

        Call<NewsParent> call = mNewsInterface.getJsonData();

        //Ejecutar
        call.enqueue(new Callback<NewsParent>() {
            @Override
            public void onResponse(Call<NewsParent> call, Response<NewsParent> response) {
                if(!response.isSuccessful())
                {
                    Log.e("Codigo ", "" +response.code());
                    return;
                }

                Log.e("data ", "" + response.body().getSources());

                List<News> news= response.body().getSources(); // guarda todos los objetos en la clase

                mAdapter = new NewsAdapter(news,context);//Creo el adapter con los datos requeridos
                binding.recyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<NewsParent> call, Throwable t) {

                Log.e("Error ", "" +t.getMessage());
            }
        });

        //Change binding


    }
}
