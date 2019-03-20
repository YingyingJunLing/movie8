package com.bw.movie.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Base64.EncryptUtil;
import com.bw.movie.R;
import com.bw.movie.mvp.model.api.Api;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.presenter.presenterimpl.LoginPresenter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<Contract.ILoginView, LoginPresenter> implements Contract.ILoginView {


    EditText loginEditPhone;
    @BindView(R.id.login_view_phone)
    View loginViewPhone;

    EditText loginEditPass;
    @BindView(R.id.login_view_pass)
    View loginViewPass;

    CheckBox loginBoxRemember;

    @BindView(R.id.login_text_reg)
    TextView loginTextReg;
    @BindView(R.id.login_button_login)
    Button loginButtonLogin;
    private LoginPresenter loginPresenter;
    private String pwd;
    private String mabile;
    private String pass;
    private SharedPreferences sp;
    private HashMap<String, String> hashMap;
    private SharedPreferences.Editor edit;
    private LoginBean.ResultBean result;
    private String userId;
    private String sessionId;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        loginEditPass =  findViewById(R.id.login_edit_pass);
        loginEditPhone = findViewById(R.id.login_edit_phone);
        loginBoxRemember =   findViewById(R.id.login_box_remember);
        loginEditPass.setEnabled(true);
        loginEditPhone.setEnabled(true);
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        //7：判断是否点击记住秘密
        boolean b = sp.getBoolean("jizhu", false);
        //8：如果记住咯
        if(b) {
            //存值   先得到点击 存的值
            String uname = sp.getString("user", "");
            String upass = sp.getString("pass", pwd);
            Log.e("upass",upass);
            //吧得到的值赋值给根据id得到的
            loginEditPhone.setText(uname);
            loginEditPass.setText(upass);
            loginBoxRemember.setChecked(b);  //跳转
            //startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        loginPresenter = new LoginPresenter();
        return loginPresenter;
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onILoginSuccess(Object o) {
        if(o!=null) {
            if (o instanceof LoginBean) {
                LoginBean loginBean = (LoginBean) o;
                if(loginBean != null) {
                    result = loginBean.getResult();
                    Log.e("result", result +"");
                    String headPic = result.getUserInfo().getHeadPic();
                    String nickName = result.getUserInfo().getNickName();
                    LoginBean.ResultBean.UserInfoBean userInfoBean = new LoginBean.ResultBean.UserInfoBean(headPic, nickName);
                    EventBus.getDefault().postSticky(userInfoBean);
                    userId = result.getUserId();
                    sessionId = result.getSessionId();
                    LoginBean.ResultBean resultBean = new LoginBean.ResultBean(userId,sessionId);
                    EventBus.getDefault().postSticky(resultBean);
                    if(loginBean.getStatus().equals("0000")) {
                        //跳转
                        Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }
                }
            }
        }

    }

    @Override
    public void onILoginFail(String errorInfo) {

    }






    @OnClick({R.id.login_text_reg, R.id.login_button_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_text_reg:
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                break;
            case R.id.login_button_login:
                pwd = loginEditPass.getText().toString().trim();
                pass = EncryptUtil.encrypt(pwd);
                Log.e("pass",pass);
                mabile = loginEditPhone.getText().toString().trim();
                hashMap = new HashMap<>();
                hashMap.put("phone",mabile);
                hashMap.put("pwd",pass);
                if(pass != null && mabile !=null)
                {
                    //5如果不为空 就存值
                    edit = sp.edit();
                    edit.putBoolean("jizhu", loginBoxRemember.isChecked());
                    edit.putString("user", mabile);
                    edit.putString("pass", pwd);
                    //启动
                    edit.commit();
                    loginPresenter.onILoginPre(Api.LOGIN, hashMap);

                }else{
                    Toast.makeText(LoginActivity.this,"账号或者密码不能为空",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }
}
