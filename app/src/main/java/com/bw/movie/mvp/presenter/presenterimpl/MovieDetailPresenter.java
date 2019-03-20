package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.MovieDetailModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

public class MovieDetailPresenter extends BasePresenter<Contract.IMovieDetailView> implements Contract.IMovieDetailPre {
    private MovieDetailModel movieDetailModel;
    public MovieDetailPresenter(){
        movieDetailModel = new MovieDetailModel();
    }
    @Override
    public void onIMovieDetailPre(int movieID) {
        movieDetailModel.onIMovieDetailModel(movieID, new Contract.MovieDetailBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIMovieDetailSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }


    public void onIMovieCommenPre(int movieId,int page, int count) {
        movieDetailModel.onIMovieCommenModel(movieId,page, count, new Contract.MovieDetailBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIMovieDetailSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onIFollowMovie(int movieId, HashMap<String, String> hashMap) {
        movieDetailModel.onIFollowMovie(movieId, hashMap, new Contract.MovieDetailBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIMovieDetailSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onICancelFollowMovie(int movieId, HashMap<String, String> hashMap) {
        movieDetailModel.onICancelFollowMovie(movieId, hashMap, new Contract.MovieDetailBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIMovieDetailSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    public void onDestroy(){
        if (movieDetailModel!=null){
            movieDetailModel=null;
        }
    }
}
