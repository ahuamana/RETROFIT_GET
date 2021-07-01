package com.paparazziteam.retrofitget.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.paparazziteam.retrofitget.R;
import com.paparazziteam.retrofitget.model.News;

import java.util.List;

public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<News> mNewsModel;
    Context context;

    public NewsAdapter(List<News> news, Context context)
    {
        this.mNewsModel = news;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_news, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        News noticiaobjeto = mNewsModel.get(position);



        //binding
        holder.txttitulo.setText(noticiaobjeto.getTitle());


        //binding image
        Glide.with(context)
                .load(noticiaobjeto.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.roundedImageView);

    }

    @Override
    public int getItemCount() {
        return mNewsModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txttitulo;
        Button btnprueba;
        RoundedImageView roundedImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txttitulo = itemView.findViewById(R.id.txtTitulo);
            btnprueba = itemView.findViewById(R.id.btnPrueba);
            roundedImageView = itemView.findViewById(R.id.roundedImageView);



        }
    }
}
