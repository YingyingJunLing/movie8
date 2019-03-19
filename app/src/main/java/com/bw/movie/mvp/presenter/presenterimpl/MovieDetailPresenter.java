package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.MovieDetailModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

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
}
