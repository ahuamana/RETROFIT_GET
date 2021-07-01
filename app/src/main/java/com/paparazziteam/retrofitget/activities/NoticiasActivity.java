package com.paparazziteam.retrofitget.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.paparazziteam.retrofitget.R;
import com.paparazziteam.retrofitget.adapters.NewsAdapter;
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

    NewsAdapter mAdapter;
    NewsInterface mNewsInterface;

    RecyclerView recyclerView;
    LinearLayoutManager layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(layout);

        getNewsSources();
    }

    private void getNewsSources() {

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

                mAdapter = new NewsAdapter(news, getApplicationContext());//Creo el adapter con los datos requeridos
                recyclerView.setAdapter(mAdapter);

//                for(News noticias: news)
//                {
//                    Log.e("title: ",""+ noticias.getTitle());
//
//                }


            }

            @Override
            public void onFailure(Call<NewsParent> call, Throwable t) {

                Log.e("Error ", "" +t.getMessage());
            }
        });


    }
}