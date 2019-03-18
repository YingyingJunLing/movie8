package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.ComingSoonMovieBean;
import com.bw.movie.mvp.model.bean.HotMovieBean;
import com.bw.movie.mvp.model.bean.RecommendMovieBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CinemaModel implements Contract.ICinemaModel {

    @Override
    public void ICinema(int page, int count, final Contract.CinemaCallBack cinemaCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getHotMovie(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotMovieBean>() {
                    @Override
                    public void accept(HotMovieBean hotMovieBean) throws Exception {
                        cinemaCallBack.onSuccess(hotMovieBean);
                    }
                });
    }

    @Override
    public void IReleaseMovie(int page, int count, final Contract.CinemaCallBack cinemaCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getReleaseMovie(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RecommendMovieBean>() {
                    @Override
                    public void accept(RecommendMovieBean recommendMovieBean) throws Exception {
                        cinemaCallBack.onSuccess(recommendMovieBean);
                    }
                });
    }

    @Override
    public void IComingSoonMovie(int page, int count, final Contract.CinemaCallBack cinemaCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getComingSoonMovie(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ComingSoonMovieBean>() {
                    @Override
                    public void accept(ComingSoonMovieBean comingSoonMovieBean) throws Exception {
                        cinemaCallBack.onSuccess(comingSoonMovieBean);
                    }
                });
    }
}
