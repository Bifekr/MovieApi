package com.example.movieapi.response;

import com.example.movieapi.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("results")
    @Expose
    private List<MovieModel> movies;

    @SerializedName("total_result")
    @Expose
    private int total_count;

    public List<MovieModel> getMovies(){
        return movies;
    }

    public int getTotal_count() {
        return total_count;
    }


    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "movies=" + movies +
                ", total_count=" + total_count +
                '}';
    }
}
