package com.paparazziteam.retrofitget.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paparazziteam.retrofitget.R;
import com.paparazziteam.retrofitget.interfaces.NewsInterface;
import com.paparazziteam.retrofitget.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsFragment extends Fragment {

    String data = "http://api.mediastack.com/v1/news?access_key=2a6960abe7dc05eaf9f2ca28738cab10&sources=cnn,bbc";

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


       getNewsSources();

       return view;
    }

    private void getNewsSources() {





    }


}