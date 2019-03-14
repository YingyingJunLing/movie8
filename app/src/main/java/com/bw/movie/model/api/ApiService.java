package com.bw.movie.model.api;

import com.bw.movie.model.bean.LoginBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {
    @POST
    Observable<LoginBean> login(@Url String url, @HeaderMap HashMap<String, String> hashMap);

}
