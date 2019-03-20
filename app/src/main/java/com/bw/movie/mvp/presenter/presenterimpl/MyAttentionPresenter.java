package com.bw.movie.mvp.presenter.presenterimpl;


import com.bw.movie.mvp.model.modelimpl.MyAttentionModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

public class MyAttentionPresenter extends BasePresenter<Contract.IAttentionView> implements Contract.IAttentionPre{


    private final MyAttentionModel myAttentionFilmModel;

    public MyAttentionPresenter()
    {
        myAttentionFilmModel = new MyAttentionModel();
    }

    @Override
    public void onIAttentionMoviePre(HashMap<String, String> hashMap, int page, int count)
    {
        myAttentionFilmModel.IAttentionMovie(hashMap, page, count, new Contract.MyAttentionCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIAttentionSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });

    }

    @Override
    public void onIAttentionCameraPres(HashMap<String, String> hashMap, int page, int count) {
        myAttentionFilmModel.IIAttentionCameras(hashMap, page, count, new Contract.MyAttentionCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIAttentionSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }
}
