package com.example.movieapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.movieapi.ViewModel.MovieListViewModel;
import com.example.movieapi.adapter.MovieRecyclerView;
import com.example.movieapi.adapter.OnMovieListener;
import com.example.movieapi.models.MovieModel;
import com.example.movieapi.request.MovieApi;
import com.example.movieapi.request.Service;
import com.example.movieapi.response.MovieSearchResponse;
import com.example.movieapi.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    MovieListViewModel movieListViewModel;
    RecyclerView rv_mainActivity;
    MovieRecyclerView movieRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListViewModel=new ViewModelProvider(this).get(MovieListViewModel.class);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        movieListViewModel.popularMovie(
                1
        );
        searchqyery();
        configRecycler();
        observeMovieApi();


    }

    private void searchqyery() {
        SearchView searchView=findViewById(R.id.search_toolbar_listActivity);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(query,1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void observeMovieApi(){
        movieListViewModel.getMovie().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {

                if (movieModels!=null)
                for (MovieModel movies:movieModels
                     ) {
                    Log.i("tag", "onChanged: " + movies.getTitle());
                    movieRecyclerAdapter.setMovie(movieModels);

                }
            }
        });
        movieListViewModel.getPopular().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels!=null)
                    for (MovieModel movies:movieModels
                    ) {
                        Log.i("tag", "onChanged: " + movies.getTitle());
                        movieRecyclerAdapter.setMovie(movieModels);

                    }
            }
        });
    }

    private void configRecycler(){
        rv_mainActivity=findViewById(R.id.rv_mainActivity);
        movieRecyclerAdapter = new MovieRecyclerView(this);

        rv_mainActivity.setAdapter(movieRecyclerAdapter);
        rv_mainActivity.setLayoutManager(new LinearLayoutManager(this));

        rv_mainActivity.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)){
                    movieListViewModel.searchNextPage();
                }
            }
        });

    }

    @Override
    public void onMovieClick(int pos) {

        Toast.makeText(this, ""+pos, Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(MovieListActivity.this,MovieDetailActivity.class);
        intent.putExtra("movie",movieRecyclerAdapter.getMovieid(pos));
        startActivity(intent);

    }

    @Override
    public void onCategoryClick(String categury) {

    }
}