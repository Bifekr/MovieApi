package com.example.movieapi.request;

import com.example.movieapi.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit retrofit=null;
    private static Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit getRetrofit(){
        if (retrofit == null) {
            retrofit = builder.build();
        }
        return retrofit;
    }

    private static MovieApi movieApi = getRetrofit().create(MovieApi.class);
    public static MovieApi getMovieApi(){
        return movieApi;
    }
}
