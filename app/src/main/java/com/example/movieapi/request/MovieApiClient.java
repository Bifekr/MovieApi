package com.example.movieapi.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapi.AppExecuters;
import com.example.movieapi.models.MovieModel;
import com.example.movieapi.response.MovieSearchResponse;
import com.example.movieapi.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    private static MovieApiClient instance;
    public static MovieApiClient getInstance(){
        if (instance==null){
            instance=new MovieApiClient();
        }return instance;
    }

    private RetrieveMovieRunnable retrieveMovieRunnable;
    private RetrievePopularMovie retrievePopularMovie;

    private MutableLiveData<List<MovieModel>> mMovie;
    private MutableLiveData<List<MovieModel>> mPopular;
    private MovieApiClient(){
        mMovie=new MutableLiveData<>();
        mPopular=new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovie(){
        return mMovie;
    }
    public LiveData<List<MovieModel>> getPopular(){
        return mPopular;
    }





    //execute and get api
    public void searchMovieApi(String query,int page_number){

        if (retrieveMovieRunnable !=null){
            retrieveMovieRunnable=null;
        }

        retrieveMovieRunnable=new RetrieveMovieRunnable(query, page_number);
        final Future myHandler = AppExecuters.getAppExecuters().networkIo().submit(retrieveMovieRunnable);

        AppExecuters.getAppExecuters().networkIo().schedule(() ->    {

            myHandler.cancel(true);

        },3, TimeUnit.MINUTES);
    }
    private class RetrieveMovieRunnable implements Runnable{

        private String query;
        private int page_number;
        boolean cancelRequest;

        public RetrieveMovieRunnable(String query, int page_number) {
            this.query = query;
            this.page_number = page_number;
            cancelRequest=false;
        }

        @Override
        public void run() {

            try {
                if (cancelRequest){
                    return;
                }
                Response response=callSearchMovie(query,page_number).execute();

                if (response.code()==200) {
                    assert response.body()!=null;
                    List<MovieModel> movieModels = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
                   if (page_number==1){
                       mMovie.postValue(movieModels);
                   }else {
                       List<MovieModel> currentMovies=mMovie.getValue();
                      currentMovies.addAll(movieModels);
                      mMovie.postValue(currentMovies);

                   }
                }else {
                    String error=response.errorBody().string();
                    Log.i("tag", "run: "+error);
                    mMovie.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMovie.postValue(null);
            }

        }


        private Call<MovieSearchResponse> callSearchMovie (String query,int page_number){

            return Service.getMovieApi().getMovieSearchResponse(Credentials.API_KEY,
                    query,page_number);
        }


        private void canceleRequest(){
            Log.v("tag" , "canceleRequest = " );
           cancelRequest = true;
        }


    }



    public void popularMovieApi(int page){

        if (retrievePopularMovie!=null)retrievePopularMovie=null;
        retrievePopularMovie=new RetrievePopularMovie(page);

       final Future dff= AppExecuters.getAppExecuters().networkIo().submit(retrievePopularMovie);
        AppExecuters.getAppExecuters().networkIo().schedule(new Runnable() {
            @Override
            public void run() {
                dff.cancel(true);
            }
        },3,TimeUnit.MINUTES);

    }
    private class RetrievePopularMovie implements Runnable{


        int page;
        boolean canceleReq;

        public RetrievePopularMovie( int page) {

            this.page = page;
            canceleReq = false;
        }

        @Override
        public void run() {

            try {
                if (canceleReq)
                    return;

                Response response=callPupolar(page).execute();
                if (response.code() == 200) {
                    List<MovieModel> movieModels2 = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());

                    if (page==1) {
                        mPopular.postValue(movieModels2);
                    } else {
                        List<MovieModel> currentPopular = mPopular.getValue();
                        currentPopular.addAll(movieModels2);
                        mPopular.postValue(currentPopular);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.i("tag", "run: " + error);

                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovie.postValue(null);
            }


        }

        private Call<MovieSearchResponse> callPupolar(int page){
             return Service.getMovieApi().getPopularMovies(Credentials.API_KEY,page);
        }



    }






}
