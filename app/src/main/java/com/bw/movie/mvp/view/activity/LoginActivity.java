package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.bw.movie.Base64.EncryptUtil;
import com.bw.movie.R;
import com.bw.movie.mvp.model.api.Api;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.presenter.presenterimpl.LoginPresenter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

public class LoginActivity extends BaseActivity<Contract.ILoginView,LoginPresenter> implements Contract.ILoginView {

    private LoginPresenter loginPresenter;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        loginPresenter = new LoginPresenter();
        return loginPresenter;
    }

    @Override
    protected void getData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("phone","15902460752");
        String encrypt = EncryptUtil.encrypt("123456");
        hashMap.put("pwd",encrypt);
        loginPresenter.onILoginPre(Api.LOGIN,hashMap);
    }

    @Override
    public void onILoginSuccess(Object o) {
        if (o instanceof LoginBean){
            LoginBean loginBean = (LoginBean) o;
            Toast.makeText(this,loginBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onILoginFail(String errorInfo) {

    }
}
