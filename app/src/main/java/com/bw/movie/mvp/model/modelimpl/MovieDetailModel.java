package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.MovieCommentBean;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailModel implements Contract.IMovieDetailModel {

    @Override
    public void onIMovieDetailModel(int movieID, final Contract.MovieDetailBack movieDetailBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getMoviesDetail(movieID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<MoviesDetailBean>() {
                    @Override
                    public void accept(MoviesDetailBean moviesDetailBean) throws Exception {
                        movieDetailBack.onSuccess(moviesDetailBean);
                    }
                });
    }

    @Override
    public void onIMovieCommenModel(int page, int count, final Contract.MovieDetailBack movieDetailBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.movieComment(page,count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<MovieCommentBean>() {
                    @Override
                    public void accept(MovieCommentBean movieCommentBean) throws Exception {
                        movieDetailBack.onSuccess(movieCommentBean);
                    }
                });
    }
}
