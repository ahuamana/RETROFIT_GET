package com.paparazziteam.retrofitget.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paparazziteam.retrofitget.R;
import com.paparazziteam.retrofitget.adapters.NewsAdapter;
import com.paparazziteam.retrofitget.interfaces.NewsInterface;
import com.paparazziteam.retrofitget.model.News;
import com.paparazziteam.retrofitget.model.NewsParent;
import com.paparazziteam.retrofitget.viewModels.NoticiasViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsFragment extends Fragment {

    private NoticiasViewModel noticiasViewModel;

    String data = "http://api.mediastack.com/v1/news?access_key=2a6960abe7dc05eaf9f2ca28738cab10&sources=cnn,bbc";

    NewsAdapter mAdapter;
    NewsInterface mNewsInterface;

    RecyclerView recyclerView;
    LinearLayoutManager layout;



    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_news, container, false);

        layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layout);

       getNewsSources();

       return view;
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

                mAdapter = new NewsAdapter(news, getContext());//Creo el adapter con los datos requeridos
                recyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<NewsParent> call, Throwable t) {

                Log.e("Error ", "" +t.getMessage());
            }
        });

        //Change binding


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        noticiasViewModel = ViewModelProviders.of(this).get(NoticiasViewModel.class);
        //Everthing to implement here
    }
}