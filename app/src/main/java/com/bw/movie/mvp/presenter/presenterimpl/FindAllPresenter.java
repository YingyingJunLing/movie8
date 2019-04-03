package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.FindAllModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

public class FindAllPresenter extends BasePresenter<Contract.IFindAllView> implements Contract.IFindAllPre {

    private FindAllModel findAllModel;

    public FindAllPresenter() {
        findAllModel = new FindAllModel();
    }

    @Override
    public void onIFindAllPre(int page, int count, String cinemaName) {
        findAllModel.onIFindAllModel(page, count, cinemaName, new Contract.FindAllBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIFindAllSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }
}
