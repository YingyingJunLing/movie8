package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.CinemaIfoBean;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.model.bean.ScheduleListBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ScheduleListModel implements Contract.IScheduleListModel {
    @Override
    public void onIScheduleListModel(int cinemasId, int movieID, final Contract.ScheduleListBack scheduleListBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getScheduleList(cinemasId,movieID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ScheduleListBean>() {
                    @Override
                    public void accept(ScheduleListBean scheduleListBean) throws Exception {
                        scheduleListBack.onSuccess(scheduleListBean);
                    }
                });
    }

    @Override
    public void onIScheduleListCinemaModel(int cinemasId, final Contract.ScheduleListBack scheduleListBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getCinemaInfo(cinemasId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaIfoBean>() {
                    @Override
                    public void accept(CinemaIfoBean cinemaIfoBean) throws Exception {
                        scheduleListBack.onSuccess(cinemaIfoBean);
                    }
                });
    }

    @Override
    public void onIScheduleListMovieModel(int movieID, final Contract.ScheduleListBack scheduleListBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getMoviesDetail(movieID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoviesDetailBean>() {
                    @Override
                    public void accept(MoviesDetailBean moviesDetailBean) throws Exception {
                        scheduleListBack.onSuccess(moviesDetailBean);
                    }
                });
    }
}
