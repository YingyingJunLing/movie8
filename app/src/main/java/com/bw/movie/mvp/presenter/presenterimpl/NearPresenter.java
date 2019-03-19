package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.NearModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

public class NearPresenter extends BasePresenter<Contract.INearView> implements Contract.INearPre {

    private NearModel nearModel;

    public NearPresenter() {
        nearModel = new NearModel();
    }

    @Override
    public void onINearPre(int page, int count) {
        nearModel.onINearModel(page, count, new Contract.NearBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onINearSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }
}
