package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.CinemaIfoBean;
import com.bw.movie.mvp.model.bean.MovieListBean;
import com.bw.movie.mvp.model.bean.ScheduleListBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MovieListModel implements Contract.IMovieListModel{
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
