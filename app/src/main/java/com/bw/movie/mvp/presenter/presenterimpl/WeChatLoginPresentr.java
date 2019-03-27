package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.WeChatLoginModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

public class WeChatLoginPresentr extends BasePresenter<Contract.IWeChatLoginView> implements Contract.IWeChatLoginPre {

    private WeChatLoginModel weChatLoginModel;

    public WeChatLoginPresentr() {
        weChatLoginModel = new WeChatLoginModel();
    }

    @Override
    public void onIWeChatLoginPre(String code) {
        weChatLoginModel.onIWeChatLoginModel(code, new Contract.WeChatLoginCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIWeChatLoginSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }
}
