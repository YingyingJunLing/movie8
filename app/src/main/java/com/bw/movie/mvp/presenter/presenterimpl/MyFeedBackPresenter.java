package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.MyFeedBackModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

public class MyFeedBackPresenter extends BasePresenter<Contract.IMyFeedBackView> implements Contract.IMyFeedBackPre
{

    private final MyFeedBackModel myFeedBackModel;

    public MyFeedBackPresenter() {
        myFeedBackModel = new MyFeedBackModel();
    }

    @Override
    public void onIMyFeedBackPre(HashMap<String, String> hashMap, String comment)
    {
        myFeedBackModel.IMyFeedBack(hashMap, comment, new Contract.MyFeedBackCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIMyFeedBackSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });

    }
}
