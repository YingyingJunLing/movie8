package com.bw.movie.mvp.model.modelimpl;

import android.util.Log;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements Contract.ILoginModel {
    @Override
    public void ILogin(String url, HashMap<String, String> hashMap, final Contract.LoginCallBack loginCallBack) {
        HashMap<String, String> map = new HashMap<>();
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.login(url,hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        Log.i("登录M",loginBean.toString());
                        loginCallBack.onSuccess(loginBean);
                    }
                });
    }
}
