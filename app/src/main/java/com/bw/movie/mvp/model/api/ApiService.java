package com.bw.movie.mvp.model.api;

import com.bw.movie.mvp.model.bean.ComingSoonMovieBean;
import com.bw.movie.mvp.model.bean.HotMovieBean;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.model.bean.ReleaseMovieBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {
    //登录
    @POST
    @FormUrlEncoded
    Observable<LoginBean> login(@Url String url, @FieldMap HashMap<String, String> hashMap);

    //热门电影类表
    @GET(Api.HOTMOVIE)
    Observable<HotMovieBean> getHotMovie(@Query("page")int page,@Query("count")int count);

    //上映电影类表
    @GET(Api.RELEASEMOVIE)
    Observable<ReleaseMovieBean> getReleaseMovie(@Query("page")int page, @Query("count")int count);

    //即将上映电影类表
    @GET(Api.COMINGSOONMOVIE)
    Observable<ComingSoonMovieBean> getComingSoonMovie(@Query("page")int page, @Query("count")int count);

    //根据电影ID查询电影详情
    @GET(Api.MOVIEDETAIL)
    Observable<MoviesDetailBean> getMoviesDetail(@Query("movieId")int movieId);

}
