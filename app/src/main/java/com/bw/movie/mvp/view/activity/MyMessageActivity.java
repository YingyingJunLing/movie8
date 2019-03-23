package com.bw.movie.mvp.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.Base64.EncryptUtil;
import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.MyMessageBean;
import com.bw.movie.mvp.model.bean.UpdatePassBean;
import com.bw.movie.mvp.model.utils.NetworkErrorUtils;
import com.bw.movie.mvp.presenter.presenterimpl.MyMessagePresenter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class MyMessageActivity extends BaseActivity<Contract.IMyMessageView,MyMessagePresenter> implements Contract.IMyMessageView {

    private TextView myMessage_name;
    private TextView myMessage_data;
    private TextView myMessage_email;
    private ImageView myMessage_head_image;
    private TextView myMessage_phone;
    private TextView myMessage_sex;
    private ImageView myMessage_fan;
    private MyMessagePresenter myMessagePresenter;
    private String userId;
    private  String sessionId;
    private HashMap<String, String> hashMap;
    private ImageView myMessage_updatePass;
    private NetworkErrorUtils networkErrorUtils;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        //注册eventBus
        EventBus.getDefault().register(this);

        setContentView(R.layout.activity_my_message);
    }

    //得到userId    sessionId
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getLoginData(LoginBean.ResultBean resultBean) {
        userId = resultBean.getUserId();
        sessionId = resultBean.getSessionId();

    }

    @Override
    protected void initView() {
        networkErrorUtils = new NetworkErrorUtils(MyMessageActivity.this);
        myMessage_name = findViewById(R.id.myMessage_name);
        myMessage_data = findViewById(R.id.myMessage_data);
        myMessage_email = findViewById(R.id.myMessage_email);
        myMessage_head_image = findViewById(R.id.myMessage_head_image);
        myMessage_phone = findViewById(R.id.myMessage_phone);
        myMessage_sex = findViewById(R.id.myMessage_sex);
        myMessage_fan = findViewById(R.id.myMessage_fan);
        myMessage_updatePass = findViewById(R.id.myMessage_updatePass);
        //返回按钮
        myMessage_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //修改密码点击事件
        //修改密码
        myMessage_updatePass.setOnClickListener(new View.OnClickListener() {

            private HashMap<String, String> params;

            @Override
            public void onClick(View v) {
                View passView = View.inflate(MyMessageActivity.this, R.layout.alert_pass, null);
                final EditText edit_pass = passView.findViewById(R.id.alert_edit_pass);
                final EditText edit_surepass1 = passView.findViewById(R.id.alert_edit_surepass1);
                final EditText edit_surepass2 = passView.findViewById(R.id.alert_edit_surepass2);
                final String oldPwd = EncryptUtil.encrypt(edit_pass.getText().toString());
                final String pass1 = EncryptUtil.encrypt(edit_surepass1.getText().toString());
                final String pass2 = EncryptUtil.encrypt(edit_surepass2.getText().toString());
                final AlertDialog.Builder builder1 = new AlertDialog
                        .Builder(MyMessageActivity.this)
                        .setView(passView);
                params = new HashMap<>();
                params.put("oldPwd", oldPwd);
                params.put("newPwd", pass1);
                params.put("newPwd2",pass2);
                Log.e("params", params +"");
                //点击对话框以外的区域是否让对话框消失
                builder1.setCancelable(false);
                //确定事件
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if( pass1 .equals(pass2))
                        {
                            myMessagePresenter.onIUpdatePassPre(hashMap, params);
                            dialog.dismiss();
                        }

                    }
                });
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder1.show();

            }
        });

        hashMap = new HashMap<>();
        hashMap.put("userId",userId);
        hashMap.put("sessionId",sessionId);
        Log.e("myMessageBean",hashMap+"");

    }

    @Override
    protected MyMessagePresenter createPresenter() {
        myMessagePresenter = new MyMessagePresenter();
        return myMessagePresenter;
    }

    @Override
    protected void getData() {

        myMessagePresenter.onIMyPre(hashMap);
    }

    @Override
    public void onIMySuccess(Object o) {
        if(o instanceof MyMessageBean)
        {
            MyMessageBean myMessageBean = (MyMessageBean) o;
            Log.e("myMessageBean",myMessageBean+"");
            if(myMessageBean != null)
            {
                myMessage_name.setText(myMessageBean.getResult().getNickName());
                myMessage_email.setText(myMessageBean.getResult().getEmail());
                myMessage_phone.setText(myMessageBean.getResult().getPhone());
                Glide.with(this)
                        .load(myMessageBean.getResult().getHeadPic())
                        .into(myMessage_head_image);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String data = simpleDateFormat.format(myMessageBean.getResult().getBirthday());
                myMessage_data.setText(data);
                if(myMessageBean.getResult().getSex().equals("1"))
                {
                    myMessage_sex.setText("男");
                }else{
                    myMessage_sex.setText("女");
                }


            }
        }

    }

    @Override
    public void onIUpdatePass(Object o) {
        if(o instanceof UpdatePassBean)
        {
            UpdatePassBean updatePassBean = (UpdatePassBean) o;
            if(updatePassBean != null)
            {
                Toast.makeText(MyMessageActivity.this,updatePassBean.getMessage(),Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onIMyFail(String errorInfo) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myMessagePresenter.onDestroy();
    }
}
