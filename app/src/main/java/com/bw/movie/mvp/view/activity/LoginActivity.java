package com.bw.movie.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Base64.EncryptUtil;
import com.bw.movie.R;
import com.bw.movie.mvp.model.api.Api;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.utils.NetworkErrorUtils;
import com.bw.movie.mvp.presenter.presenterimpl.LoginPresenter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;
import com.bw.movie.wxapi.WXEntryActivity;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;

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
    @BindView(R.id.login_image_phone)
    ImageView loginImagePhone;
    @BindView(R.id.login_image_pass_lock)
    ImageView loginImagePassLock;
    @BindView(R.id.login_image_pass_eye)
    ImageView loginImagePassEye;
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
    private ImageView image_eye;
    public int i = 1;
    private NetworkErrorUtils networkErrorUtils;
    private ImageView weixinLogin;
    private IWXAPI wxapi;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        //通过WXAPIFactory工厂获取IWXApI的示例
        wxapi = WXAPIFactory.createWXAPI(this, "wxb3852e6a6b7d9516", true);
        //将应用的appid注册到微信
        wxapi.registerApp("wxb3852e6a6b7d9516");
    }

    @Override
    protected void initView() {
        networkErrorUtils = new NetworkErrorUtils(LoginActivity.this);

        loginEditPass = findViewById(R.id.login_edit_pass);
        loginEditPhone = findViewById(R.id.login_edit_phone);
        loginBoxRemember = findViewById(R.id.login_box_remember);
        image_eye = findViewById(R.id.login_image_pass_eye);
        weixinLogin = findViewById(R.id.weixin_login);
        loginEditPass.setEnabled(true);
        loginEditPhone.setEnabled(true);
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        //7：判断是否点击记住秘密
        boolean b = sp.getBoolean("jizhu", false);
        //8：如果记住咯
        if (b) {
            //存值   先得到点击 存的值
            String uname = sp.getString("user", "");
            String upass = sp.getString("pass", pwd);
            Log.e("upass", upass);
            //吧得到的值赋值给根据id得到的
            loginEditPhone.setText(uname);
            loginEditPass.setText(upass);
            loginBoxRemember.setChecked(b);  //跳转

        }
        image_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 1) {//从密码不可见模式变为密码可见模式
                    loginEditPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    i = 2;
                } else {
                    //从密码可见模式变为密码不可见模式
                    loginEditPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    i = 1;
                }
            }
        });
        weixinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "diandi_wx_login";
                wxapi.sendReq(req);
            }
        });

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
        if (o != null) {
            if (o instanceof LoginBean) {
                LoginBean loginBean = (LoginBean) o;
                if (loginBean != null) {
                    result = loginBean.getResult();
                    Log.e("result", result + "");
                    String headPic = result.getUserInfo().getHeadPic();
                    String nickName = result.getUserInfo().getNickName();
                    LoginBean.ResultBean.UserInfoBean userInfoBean = new LoginBean.ResultBean.UserInfoBean(headPic, nickName);
                    EventBus.getDefault().postSticky(userInfoBean);
                    userId = result.getUserId();
                    sessionId = result.getSessionId();
                    LoginBean.ResultBean resultBean = new LoginBean.ResultBean(userId, sessionId);
                    EventBus.getDefault().postSticky(resultBean);
                    if (loginBean.getStatus().equals("0000")) {
                        //跳转
                        Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
                Log.e("pass", pass);
                mabile = loginEditPhone.getText().toString().trim();
                hashMap = new HashMap<>();
                hashMap.put("phone", mabile);
                hashMap.put("pwd", pass);
                if (pass != null && mabile != null) {
                    //5如果不为空 就存值
                    edit = sp.edit();
                    edit.putBoolean("jizhu", loginBoxRemember.isChecked());
                    edit.putString("user", mabile);
                    edit.putString("pass", pwd);
                    //启动
                    edit.commit();

                    loginPresenter.onILoginPre(Api.LOGIN, hashMap);

                } else {
                    Toast.makeText(LoginActivity.this, "账号或者密码不能为空", Toast.LENGTH_SHORT).show();
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
