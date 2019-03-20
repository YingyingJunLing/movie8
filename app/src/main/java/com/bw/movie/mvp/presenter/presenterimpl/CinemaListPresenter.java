package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.CinemaListModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

public class CinemaListPresenter extends BasePresenter<Contract.ICinemaListView> implements Contract.ICinemaListPre {

    private CinemaListModel cinemaListModel;

    public CinemaListPresenter() {
        cinemaListModel = new CinemaListModel();
    }

    @Override
    public void onICinemaListPre(int movieID) {
        cinemaListModel.onICinemaListModel(movieID, new Contract.CinemaListBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onICinemaListSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }
}
