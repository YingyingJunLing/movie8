package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.MyMessageBean;
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
        myMessage_name = findViewById(R.id.myMessage_name);
        myMessage_data = findViewById(R.id.myMessage_data);
        myMessage_email = findViewById(R.id.myMessage_email);
        myMessage_head_image = findViewById(R.id.myMessage_head_image);
        myMessage_phone = findViewById(R.id.myMessage_phone);
        myMessage_sex = findViewById(R.id.myMessage_sex);
        myMessage_fan = findViewById(R.id.myMessage_fan);
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
        //myMessagePresenter.onIMyPre(hashMap);
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                String data = simpleDateFormat.format(myMessageBean.getResult().getBirthday());
                myMessage_data.setText(data);
                myMessage_sex.setText(myMessageBean.getResult().getSex());
            }
        }

    }

    @Override
    public void onIMyFail(String errorInfo) {

    }
}
