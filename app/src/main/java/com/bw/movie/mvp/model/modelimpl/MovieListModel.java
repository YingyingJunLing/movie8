package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.CinemaCommentGreatBean;
import com.bw.movie.mvp.model.bean.CinemaIfoBean;
import com.bw.movie.mvp.model.bean.FindCinemaCommentBean;
import com.bw.movie.mvp.model.bean.FindCinemaInfoBean;
import com.bw.movie.mvp.model.bean.MovieListBean;
import com.bw.movie.mvp.model.bean.ScheduleListBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MovieListModel implements Contract.IMovieListModel{
    @Override
    public void IMyFeedBack(HashMap<String, String> hashMap, int cinemaId, final Contract.MyFeedBackCallBack myFeedBackCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.findCinemaInfo(hashMap,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FindCinemaInfoBean>() {
                    @Override
                    public void accept(FindCinemaInfoBean findCinemaInfoBean) throws Exception {
                        myFeedBackCallBack.onSuccess(findCinemaInfoBean);
                    }
                });
    }

    @Override
    public void onICimemaCommentModel(HashMap<String, String> hashMap, int cinemaId, int page, int count, final Contract.MovieListBack movieListBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.cinemaComment(hashMap,cinemaId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FindCinemaCommentBean>() {
                    @Override
                    public void accept(FindCinemaCommentBean cinemaCommentBean) throws Exception {
                        movieListBack.onSuccess(cinemaCommentBean);
                    }
                });
    }


    public void onICimemaCommentGreateModel(HashMap<String, String> hashMap, int cinemaId, final Contract.MovieListBack movieListBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.cinemaCommentGreate(hashMap,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaCommentGreatBean>() {
                    @Override
                    public void accept(CinemaCommentGreatBean cinemaCommentGreatBean) throws Exception {
                        movieListBack.onSuccess(cinemaCommentGreatBean);
                    }
                });
    }

    @Override
    public void onIMovieListModel(int cinemaId, final Contract.MovieListBack movieListBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getMovieList(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieListBean>() {
                    @Override
                    public void accept(MovieListBean movieListBean) throws Exception {
                        movieListBack.onSuccess(movieListBean);
                    }
                });
    }

    @Override
    public void onIMovieListCinemaModel(int cinemaId, final Contract.MovieListBack movieListBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getCinemaInfo(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaIfoBean>() {
                    @Override
                    public void accept(CinemaIfoBean cinemaIfoBean) throws Exception {
                        movieListBack.onSuccess(cinemaIfoBean);
                    }
                });
    }

    @Override
    public void onIMovieListCinemaMovieModel(int cinemasId, int movieId, final Contract.MovieListBack movieListBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getScheduleList(cinemasId,movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ScheduleListBean>() {
                    @Override
                    public void accept(ScheduleListBean scheduleListBean) throws Exception {
                        movieListBack.onSuccess(scheduleListBean);
                    }
                });
    }
}
