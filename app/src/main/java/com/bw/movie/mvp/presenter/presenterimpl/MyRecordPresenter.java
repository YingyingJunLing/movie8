package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.MyRecordModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

public class MyRecordPresenter extends BasePresenter<Contract.IMyRecotdView> implements Contract.IMyRecotdPre {

    private final MyRecordModel myRecordModel;

    public MyRecordPresenter() {
        myRecordModel = new MyRecordModel();

    }

    @Override
    public void onIMyRecotdPre(HashMap<String, String> hashMap, int page, int count, String status) {
        myRecordModel.IMyRecotd(hashMap, page, count, status, new Contract.MyRecotdCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIMyRecotdSuccess(o);

            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }
}
