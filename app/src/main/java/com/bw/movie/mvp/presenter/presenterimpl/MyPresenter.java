package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

public class MyPresenter extends BasePresenter<Contract.IMyView> implements Contract.IMyPre {
    @Override
    public void onIMyPre(String url) {

    }
}
