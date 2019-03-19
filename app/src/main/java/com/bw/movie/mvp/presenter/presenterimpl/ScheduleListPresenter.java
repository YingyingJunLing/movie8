package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.ScheduleListModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

public class ScheduleListPresenter extends BasePresenter<Contract.IScheduleListView> implements Contract.IScheduleListPre {

    private ScheduleListModel scheduleListModel;

    public ScheduleListPresenter() {
        scheduleListModel = new ScheduleListModel();
    }

    @Override
    public void onIScheduleListPre(int cinemasId, int movieID) {
        scheduleListModel.onIScheduleListModel(cinemasId, movieID, new Contract.ScheduleListBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIScheduleListSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onIScheduleListCinemaPre(final int cinemasId) {
        scheduleListModel.onIScheduleListCinemaModel(cinemasId, new Contract.ScheduleListBack() {
            @Override
            public void onSuccess(Object o) {
                scheduleListModel.onIScheduleListCinemaModel(cinemasId, new Contract.ScheduleListBack() {
                    @Override
                    public void onSuccess(Object o) {
                        getView().onIScheduleListCinemaSuccess(o);
                    }

                    @Override
                    public void onFail(String errorInfo) {

                    }
                });
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    @Override
    public void onIScheduleListMoviePre(int movieID) {
        scheduleListModel.onIScheduleListMovieModel(movieID, new Contract.ScheduleListBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIScheduleListMovieSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }
}
