package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.MovieListModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

public class MovieListPresenter extends BasePresenter<Contract.IMovieListView> implements Contract.IMovieListPre {

    private MovieListModel movieListModel;

    public MovieListPresenter() {
        movieListModel = new MovieListModel();
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
}
