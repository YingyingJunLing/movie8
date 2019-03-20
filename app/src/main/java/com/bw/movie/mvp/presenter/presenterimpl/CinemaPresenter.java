package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.CinemaModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

public class CinemaPresenter extends BasePresenter<Contract.ICinemaView> implements Contract.ICinemaPre {
    private CinemaModel cinemaModel;

    public CinemaPresenter() {
        cinemaModel = new CinemaModel();
    }

    @Override
    public void onICinemaPre(int page, int count) {
        cinemaModel.ICinema(page, count, new Contract.CinemaCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onICinemaSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onIReleaseMovie(int page, int count) {
        cinemaModel.IReleaseMovie(page, count, new Contract.CinemaCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIReleaseMovieSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onIComingSoonMovie(int page, int count) {
        cinemaModel.IComingSoonMovie(page, count, new Contract.CinemaCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIComingSoonMovieSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    public void onDestory(){
        if (cinemaModel!=null){
            cinemaModel=null;
        }
    }
}
