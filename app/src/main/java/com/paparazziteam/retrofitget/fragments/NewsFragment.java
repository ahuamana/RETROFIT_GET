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
import com.paparazziteam.retrofitget.databinding.FragmentNewsBinding;
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
    LinearLayoutManager layout;

    //FragmentNewsBinding --> NewsFragment
    private FragmentNewsBinding binding;
    private NoticiasViewModel viewModel = new NoticiasViewModel();

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
        binding = FragmentNewsBinding.inflate(inflater, container, false);

        layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //recyclerView = binding.findViewById(R.id.recyclerView);
        binding.recyclerView.setLayoutManager(layout);
        viewModel.getNewsSources(binding,getContext());

       return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        noticiasViewModel = ViewModelProviders.of(this).get(NoticiasViewModel.class);
        //Everthing to implement here
    }

}