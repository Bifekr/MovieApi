package com.example.movieapi;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class AppExecuters {

    private static AppExecuters appExecuters;
    public static AppExecuters getAppExecuters(){
        if (appExecuters==null){
            appExecuters=new AppExecuters();
        }return appExecuters;
    }


    private final ScheduledExecutorService mNetworkIo = Executors.newScheduledThreadPool(3);
    public ScheduledExecutorService networkIo(){
        return mNetworkIo;
    }


}


