package com.bw.movie.mvp.view.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.Base64.EncryptUtil;
import com.bw.movie.R;
import com.bw.movie.mvp.model.api.Api;

import com.bw.movie.mvp.model.bean.RegBean;
import com.bw.movie.mvp.model.utils.AccountValidatorUtil;
import com.bw.movie.mvp.model.utils.NetworkErrorUtils;
import com.bw.movie.mvp.presenter.presenterimpl.RegPresenter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.Calendar;
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
    private String email;
    private String mobile;
    private String pwd;
    private String sex;
    private String pass;
    private RegBean regBean;
    private NetworkErrorUtils networkErrorUtils;
    private int mYear, mMonth, mDay;
    private  String mBirthDay;
    private EditText reg_edit_data;


    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        networkErrorUtils = new NetworkErrorUtils(RegActivity.this);
        reg_edit_data = findViewById(R.id.reg_edit_data);
        reg_edit_data.setInputType(InputType.TYPE_NULL);
        findViewById(R.id.reg_edit_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowBirthDialog();
            }
        });

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
        boolean username = AccountValidatorUtil.isUsername(name);
        if(username)
        {
            regEditName.setText(name);
        }
        reg_edit_data.setText(mBirthDay);

        email = regEditEmail.getText().toString();
        boolean emails = AccountValidatorUtil.isEmail(this.email);
        if(emails)
        {
            regEditEmail.setText(name);
        }
        mobile = regEditMobile.getText().toString();
        boolean mobiles = AccountValidatorUtil.isMobile(this.mobile);
        if(mobiles)
        {
            regEditMobile.setText(mobile);
        }
        pwd = regEditPwd.getText().toString();
        boolean password = AccountValidatorUtil.isPassword(pwd);
        if(password)
        {
            regEditPwd.setText(pwd);
        }
        pass = EncryptUtil.encrypt(pwd);
        sex = regEditSex.getText().toString();

        HashMap<String ,String > hashMap = new HashMap<>();
        hashMap.put("nickName",name);
        hashMap.put("phone", this.mobile);
        hashMap.put("pwd2",pass);
        hashMap.put("imei","123456");
        hashMap.put("ua","小米");
        hashMap.put("screenSize","5.0");
        hashMap.put("os","android");
        hashMap.put("pwd",pass);
        hashMap.put("sex",sex);
        hashMap.put("birthday",mBirthDay);
        hashMap.put("email", this.email);
        if(username && mobiles && password && emails)
        {
            if (name !=null  && this.email !=null && this.mobile !=null &&pass !=null && sex !=null)
            {
                regPresenter.onILoginPre(Api.REG,hashMap);

            }if(name.equals("") || this.email.equals("") || this.mobile.equals("") || pwd.equals("") ||sex.equals("") ) {
            Toast.makeText(RegActivity.this,"输入内容有误，请检查",Toast.LENGTH_SHORT).show();
        }
        }else{
            Toast.makeText(RegActivity.this,"输入内容有误，请检查",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        regPresenter.onDestroy();
    }
    /**
     * 出生日期的弹框
     */
    private void ShowBirthDialog() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            new DatePickerDialog(RegActivity.this, R.style.DatePickThemeDialog, mDateSetListener, mYear, mMonth, mDay)
                    .show();
        }else new DatePickerDialog(RegActivity.this,mDateSetListener, mYear, mMonth, mDay)
                .show();
    }

    /**
     * 选择日期
     */
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            if (view.isShown()) {
                mYear = year;
                String mm;
                String dd;
                if (monthOfYear < 9) {
                    mMonth = monthOfYear + 1;
                    mm = "0" + mMonth;
                } else {
                    mMonth = monthOfYear + 1;
                    mm = String.valueOf(mMonth);
                }
                if (dayOfMonth < 10) {
                    mDay = dayOfMonth;
                    dd = "0" + mDay;
                } else {
                    mDay = dayOfMonth;
                    dd = String.valueOf(mDay);
                }

                mMonth = monthOfYear;

                mBirthDay = mYear + "-" + mm + "-" + dd;
                Log.e("birthday",mBirthDay.toString());
                reg_edit_data.setText(mBirthDay);
                /*  submitAccountInfo();*/
            }
        }

    };




}
