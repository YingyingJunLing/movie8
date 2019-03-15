package com.bw.movie.mvp.model.api;

import com.bw.movie.mvp.model.bean.LoginBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {
    @POST
    @FormUrlEncoded
    Observable<LoginBean> login(@Url String url, @FieldMap HashMap<String, String> hashMap);

}
