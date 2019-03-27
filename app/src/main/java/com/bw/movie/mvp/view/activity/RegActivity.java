package com.bw.movie.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.Base64.EncryptUtil;
import com.bw.movie.R;
import com.bw.movie.mvp.model.api.Api;

import com.bw.movie.mvp.model.bean.RegBean;
import com.bw.movie.mvp.model.utils.NetworkErrorUtils;
import com.bw.movie.mvp.presenter.presenterimpl.RegPresenter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends BaseActivity<Contract.ILoginView, RegPresenter> implements Contract.ILoginView {

    @BindView(R.id.reg_edit_name)
    EditText regEditName;
    @BindView(R.id.reg_view_phone)
    View regViewPhone;
    @BindView(R.id.reg_edit_sex)
    EditText regEditSex;
    @BindView(R.id.reg_view_pass)
    View regViewPass;
    @BindView(R.id.reg_edit_data)
    EditText regEditData;
    @BindView(R.id.reg_image_pass_lock)
    ImageView regImagePassLock;
    @BindView(R.id.reg_edit_mobile)
    EditText regEditMobile;
    @BindView(R.id.reg_edit_email)
    EditText regEditEmail;
    @BindView(R.id.reg_edit_pwd)
    EditText regEditPwd;
    @BindView(R.id.reg_button_reg)
    Button regButtonReg;
    private RegPresenter regPresenter;
    private String name;
    private String data;
    private String email;
    private String mobile;
    private String pwd;
    private String sex;
    private String pass;
    private RegBean regBean;
    private NetworkErrorUtils networkErrorUtils;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        networkErrorUtils = new NetworkErrorUtils(RegActivity.this);


    }

    @Override
    protected RegPresenter createPresenter() {
        regPresenter = new RegPresenter();
        return regPresenter;
    }

    @Override
    protected void getData()
    {



    }

    @Override
    public void onILoginSuccess(Object o) {
        if (o instanceof RegBean) {
            regBean = (RegBean) o;
            Toast.makeText(this, regBean.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegActivity.this, LoginActivity.class);
            intent.putExtra("phone",mobile);
            intent.putExtra("pass",pass);
            startActivity(intent);
        }

    }

    @Override
    public void onILoginFail(String errorInfo) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.reg_button_reg)
    public void onViewClicked()
    {
        name = regEditName.getText().toString();
        data = regEditData.getText().toString();
        email = regEditEmail.getText().toString();
        mobile = regEditMobile.getText().toString();
        pwd = regEditPwd.getText().toString();
        pass = EncryptUtil.encrypt(pwd);
        sex = regEditSex.getText().toString();

        HashMap<String ,String > hashMap = new HashMap<>();
        hashMap.put("nickName",name);
        hashMap.put("phone",mobile);
        hashMap.put("pwd2",pass);
        hashMap.put("imei","123456");
        hashMap.put("ua","小米");
        hashMap.put("screenSize","5.0");
        hashMap.put("os","android");
        hashMap.put("pwd",pass);
        hashMap.put("sex",sex);
        hashMap.put("birthday",data);
        hashMap.put("email",email);

        if (name !=null  && data!=null &&email!=null && mobile !=null &&pass !=null && sex !=null)
        {

            regPresenter.onILoginPre(Api.REG,hashMap);

        }else {
            Toast.makeText(RegActivity.this,"失败了",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        regPresenter.onDestroy();
    }
}
