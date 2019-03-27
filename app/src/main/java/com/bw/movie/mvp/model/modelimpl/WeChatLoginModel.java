package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WeChatLoginModel implements Contract.IWeChatLoginModel {
    @Override
    public void onIWeChatLoginModel(String code, final Contract.WeChatLoginCallBack weChatLoginCallBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getWeChatLogin(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        weChatLoginCallBack.onSuccess(loginBean);
                    }
                });
    }
}
