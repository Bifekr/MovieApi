package com.example.movieapi.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapi.models.MovieModel;
import com.example.movieapi.repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository=MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovie(){
        return movieRepository.getMovie();
    }
    public LiveData<List<MovieModel>> getPopular(){
        return movieRepository.getPopular();
    }


    public void searchMovieApi(String query,int page){
        movieRepository.searchMovieApi(query, page);
    }
    public void searchNextPage(){
        movieRepository.searchNextPage();
    }


    public void popularMovie(int page){
        movieRepository.popularMovie(page);
    }
}
