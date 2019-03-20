package com.bw.movie.mvp.presenter.presenterimpl;

import android.util.Log;

import com.bw.movie.mvp.model.modelimpl.LoginModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

public class LoginPresenter extends BasePresenter<Contract.ILoginView> implements Contract.ILoginPre {
    private LoginModel loginModel;

    public LoginPresenter(){
        loginModel = new LoginModel();
    }

    @Override
    public void onILoginPre(String url, HashMap<String, String> hashMap) {
        loginModel.ILogin(url, hashMap, new Contract.LoginCallBack() {
            @Override
            public void onSuccess(Object o) {
                Log.i("登录P",o.toString());
                getView().onILoginSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }

    public void onDestroy(){
        if (loginModel!=null){
            loginModel=null;
        }
    }
}
