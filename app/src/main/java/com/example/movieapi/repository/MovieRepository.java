package com.example.movieapi.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapi.models.MovieModel;
import com.example.movieapi.request.MovieApiClient;

import java.util.List;
import java.util.LongSummaryStatistics;

public class MovieRepository {

    private static MovieRepository instance;
    public static MovieRepository getInstance(){
        if (instance==null){
            instance=new MovieRepository();
        }

        return instance;
    }

    private String mQuery;
    private int mPage;


   private final MovieApiClient movieApiClient;

    private MovieRepository(){
      movieApiClient=MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovie(){
        return movieApiClient.getMovie();
    }

    public LiveData<List<MovieModel>> getPopular(){
        return movieApiClient.getPopular();
    }

    public void searchMovieApi(String query,int pageNumber){
        mQuery=query;
        mPage=pageNumber;
        movieApiClient.searchMovieApi(query,pageNumber);
    }

    public void searchNextPage(){
        searchMovieApi(mQuery,mPage+1);
    }
    public void popularMovie(int page){
        movieApiClient.popularMovieApi(page);
    }
}


