package com.bw.movie.wxapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.presenter.presenterimpl.WeChatLoginPresentr;
import com.bw.movie.mvp.view.activity.LoginActivity;
import com.bw.movie.mvp.view.activity.MainActivity;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends BaseActivity<Contract.IWeChatLoginView,WeChatLoginPresentr> implements IWXAPIEventHandler,Contract.IWeChatLoginView{

    private IWXAPI api;
    private String code;
    private String userId;
    private String sessionId;
    private WeChatLoginPresentr weChatLoginPresentr;

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onResp(BaseResp resp) {
        if(resp instanceof SendAuth.Resp){
            SendAuth.Resp newResp = (SendAuth.Resp) resp;
            //获取微信传回的code
            code = newResp.code;
            Log.i("获取微信传回的code", code);
        }
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("支付结果");
            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
            builder.show();
        }
    }

    @Override
    public void onIWeChatLoginFail(String errorInfo) {

    }

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        //注册API
        api = WXAPIFactory.createWXAPI(this, "wxb3852e6a6b7d9516");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected WeChatLoginPresentr createPresenter() {
        weChatLoginPresentr = new WeChatLoginPresentr();
        return weChatLoginPresentr;
    }

    @Override
    protected void getData() {
        weChatLoginPresentr.onIWeChatLoginPre(code);
    }

    @Override
    public void onIWeChatLoginSuccess(Object o) {
        Log.i("微信登录",o.toString());
        if (o instanceof LoginBean){
            LoginBean loginBean = (LoginBean) o;
            LoginBean.ResultBean result = loginBean.getResult();
            Log.i("微信登录",loginBean.getMessage());
            if (loginBean.getStatus().equals("0000")) {
                //跳转
                Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                userId = result.getUserId();
                sessionId = result.getSessionId();
                LoginBean.ResultBean resultBean = new LoginBean.ResultBean(userId, sessionId);
                EventBus.getDefault().postSticky(resultBean);
                startActivity(new Intent(WXEntryActivity.this, MainActivity.class));
                finish();
                LoginActivity.loginActivity.finish();
            }

        }
    }

}