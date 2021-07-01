package com.paparazziteam.retrofitget.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.paparazziteam.retrofitget.R;
import com.paparazziteam.retrofitget.interfaces.NewsInterface;
import com.paparazziteam.retrofitget.model.News;
import com.paparazziteam.retrofitget.model.NewsParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticiasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        getNewsSources();
    }

    private void getNewsSources() {

        Log.e("Codigo ", "inicial");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.mediastack.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //call interface
        NewsInterface newsInterface = retrofit.create(NewsInterface.class);

        Call<NewsParent> call = newsInterface.getJsonData();

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

                 List<News> news= response.body().getSources();


                for(News noticias: news)
                {
                    Log.e("title: ",""+ noticias.getTitle());

                }


            }

            @Override
            public void onFailure(Call<NewsParent> call, Throwable t) {

                Log.e("Error ", "" +t.getMessage());
            }
        });


    }
}