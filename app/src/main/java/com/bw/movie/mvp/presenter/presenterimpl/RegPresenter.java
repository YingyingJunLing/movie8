package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.RegModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

public class RegPresenter extends BasePresenter<Contract.ILoginView> implements Contract.ILoginPre
{

    private RegModel regModel;

    public RegPresenter() {
        regModel = new RegModel();
    }

    @Override
    public void onILoginPre(String url, HashMap<String, String> hashMap)
    {
        regModel.ILogin(url, hashMap, new Contract.LoginCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onILoginSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    public void onDestroy(){
        if (regModel!=null){
            regModel=null;
        }
    }
}
