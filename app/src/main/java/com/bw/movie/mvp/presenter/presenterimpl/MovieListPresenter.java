package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.MovieListModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

public class MovieListPresenter extends BasePresenter<Contract.IMovieListView> implements Contract.IMovieListPre {

    private MovieListModel movieListModel;

    public MovieListPresenter() {
        movieListModel = new MovieListModel();
    }

    @Override
    public void onIFindCimeraInfoPre(HashMap<String, String> hashMap, int cinemaId) {
        movieListModel.IMyFeedBack(hashMap, cinemaId, new Contract.MyFeedBackCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIFindCimeraInfoSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onIMovieListPre(int cinemasId) {
        movieListModel.onIMovieListModel(cinemasId, new Contract.MovieListBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIMovieListSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onIMovieListCinemaPre(int cinemasId) {
        movieListModel.onIMovieListCinemaModel(cinemasId, new Contract.MovieListBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIMovieListCinemaSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onIMovieListCinemaMoviePre(int cinemasId, int movieId) {
        movieListModel.onIMovieListCinemaMovieModel(cinemasId, movieId, new Contract.MovieListBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIMovieListCinemaMovieSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onICimemaCommentPre(HashMap<String, String> hashMap, int cinemaId, int page, int count) {
        movieListModel.onICimemaCommentModel(hashMap,cinemaId, page,cinemaId, new Contract.MovieListBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onICimemaCommentSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onICimemaCommentGreatePre(HashMap<String, String> hashMap, int cinemaId) {
        movieListModel.onICimemaCommentGreateModel(hashMap,cinemaId, new Contract.MovieListBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onICimemaCommentGreateSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    public void onDestroy(){
        if (movieListModel!=null){
            movieListModel=null;
        }
    }
}
