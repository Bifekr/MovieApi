package com.example.movieapi.response;

import com.example.movieapi.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {


    @SerializedName("results")
    @Expose
    private MovieModel movieModel;

    public MovieModel getMovieResponse(){
        return movieModel;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movieModel=" + movieModel +
                '}';
    }
}
