package com.example.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapi.models.MovieModel;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView iv_detail;
    TextView tvTitle_detail,tvOveriew_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        iv_detail=findViewById(R.id.iv_detailActivity);
        tvTitle_detail=findViewById(R.id.tvTitle_detailActivity);
        tvOveriew_detail=findViewById(R.id.tvOveriew_detialAcivity);

        getDataFromParselable();
    }

    private void getDataFromParselable() {
        if (getIntent().hasExtra("movie")){

            MovieModel movieModel=getIntent().getParcelableExtra("movie");
            Log.i("TAG", "getDataFromParselable: " +movieModel.getId());

        }
    }
}