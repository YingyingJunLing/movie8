package com.bw.movie.mvp.view.contract;

import java.util.HashMap;

public class Contract {
    public interface ILoginView{

        void onILoginSuccess(Object o);

        void onILoginFail(String errorInfo);

    }
    public interface ILoginPre{

        void onILoginPre(String url, HashMap<String, String> hashMap);

    }
    public interface ILoginModel{
        void ILogin(String url, HashMap<String, String> hashMap, LoginCallBack loginCallBack);
    }

    public interface LoginCallBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }
}
