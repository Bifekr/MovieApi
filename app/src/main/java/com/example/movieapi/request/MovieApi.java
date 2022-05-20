package com.example.movieapi.request;

import com.example.movieapi.models.MovieModel;
import com.example.movieapi.response.MovieResponse;
import com.example.movieapi.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    //https://api.themoviedb.org/3/search/movie?api_key=4d47df412982ceebfee953c580aea342&query=jack reacher

    @GET("3/search/movie")
    Call<MovieSearchResponse> getMovieSearchResponse(
            @Query("api_key") String  key,
            @Query("query") String query,
            @Query("page") int page);



    @GET("3/movie/popular/")
    Call<MovieSearchResponse> getPopularMovies(@Query("api_key") String apiKey,
                                               @Query("page") int page);

}
