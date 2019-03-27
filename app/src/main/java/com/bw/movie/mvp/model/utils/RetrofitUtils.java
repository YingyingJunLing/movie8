package com.bw.movie.mvp.model.utils;

import com.bw.movie.mvp.model.api.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils
{
    private static volatile RetrofitUtils instance;
    private final Retrofit retrofit;

    public static RetrofitUtils getInstance(){
        if (instance==null){
            synchronized (RetrofitUtils.class){
                if (instance==null){
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    private OkHttpClient okHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5000,TimeUnit.MILLISECONDS)
                .readTimeout(5000,TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .build();
        return client;
    }

    private RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient())
                .build();
    }

    public  <T> T create(Class<T> classes){
        return retrofit.create(classes);
    }
}
