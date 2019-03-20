package com.bw.movie.mvp.presenter.presenterimpl;

import android.util.Log;

import com.bw.movie.mvp.model.modelimpl.MyMessageModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;


import java.util.HashMap;

public class MyMessagePresenter extends BasePresenter<Contract.IMyMessageView> implements Contract.IMyMessagePre {

    private final MyMessageModel myMessageModel;

    public MyMessagePresenter()
    {
        myMessageModel = new MyMessageModel();
    }

    @Override
    public void onIMyPre(HashMap<String, String> hashMap) {
        myMessageModel.IMy(hashMap, new Contract.MyMessageCallBack() {
            @Override
            public void onSuccess(Object o) {
                Log.e("myMessageBean",o.toString());
                getView().onIMySuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });

    }
}
