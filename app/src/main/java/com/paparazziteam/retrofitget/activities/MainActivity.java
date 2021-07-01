package com.paparazziteam.retrofitget.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.paparazziteam.retrofitget.R;
import com.paparazziteam.retrofitget.interfaces.PostsInterface;
import com.paparazziteam.retrofitget.model.ModelPosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    TextView mJsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTextView = findViewById(R.id.jsonText);

        //llamar a retrofit
        getPost();
    }


     public void getPost()
     {
         Retrofit retrofit =  new Retrofit.Builder()
                 .baseUrl("https://jsonplaceholder.typicode.com/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

         //call interface
         PostsInterface postsInterface = retrofit.create(PostsInterface.class);

         Call<List<ModelPosts>> call = postsInterface.getDataAPI();

         //Ejecutar
         call.enqueue(new Callback<List<ModelPosts>>() {
             @Override
             public void onResponse(Call<List<ModelPosts>> call, Response<List<ModelPosts>> response) {

                 if(!response.isSuccessful())
                 {
                    mJsonTextView.setText("Codigo "  + response.code());
                    return;
                 }

                 List<ModelPosts> postsList = response.body(); // ya tenemos en el modelo toda la lista

                 for(ModelPosts posts: postsList)
                 {
                     String content = "";
                     content += "userId:" + posts.getUserId() + "\n";
                     content += "id:" + posts.getId() + "\n";
                     content += "title:" + posts.getTitle() + "\n";
                     content += "body:" + posts.getBody() + "\n\n";
                     mJsonTextView.append(content);
                 }

             }

             @Override
             public void onFailure(Call<List<ModelPosts>> call, Throwable t) {

                 mJsonTextView.setText(t.getMessage()); // mostrara el error para traer los datos
             }
         });
     }

}