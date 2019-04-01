package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.MyRecordBean;
import com.bw.movie.mvp.presenter.presenterimpl.MyRecordPresenter;
import com.bw.movie.mvp.view.adapter.MyRecordAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

public class MyRecordActivity extends BaseActivity<Contract.IMyRecotdView,MyRecordPresenter> implements Contract.IMyRecotdView
{

    private MyRecordPresenter myRecordPresenter;
    private RecyclerView my_record_recy;
    private LinearLayoutManager linearLayoutManager;
    private MyRecordBean myRecordBean;
    private MyRecordAdapter myRecordAdapter;
    private String status= "1" ;
    int page = 1;
    int count =10;
    String userId;
    String sessionId;
    private HashMap<String, String> hashMap;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_my_record);
    }
    //得到userId    sessionId
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getLoginData(LoginBean.ResultBean resultBean) {
        userId = resultBean.getUserId();
        sessionId = resultBean.getSessionId();

    }
    @Override
    protected void initView()
    {
        hashMap = new HashMap<>();
        hashMap.put("userId",userId);
        hashMap.put("sessionId",sessionId);
        my_record_recy = findViewById(R.id.my_record_recy);
       ImageView fan =  findViewById(R.id.fan);
        linearLayoutManager = new LinearLayoutManager(MyRecordActivity.this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        my_record_recy.setLayoutManager(linearLayoutManager);
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected MyRecordPresenter createPresenter() {
        myRecordPresenter = new MyRecordPresenter();
        return myRecordPresenter;
    }

    @Override
    protected void getData() {
        myRecordPresenter.onIMyRecotdPre(hashMap,page,count,status);
    }


    @Override
    public void onIMyRecotdSuccess(Object o) {
        if(o instanceof MyRecordBean)
        {

            myRecordBean = (MyRecordBean) o;
            myRecordAdapter = new MyRecordAdapter(MyRecordActivity.this, myRecordBean,hashMap);
            my_record_recy.setAdapter(myRecordAdapter);
        }

    }

    @Override
    public void onIMyRecotdFail(String errorInfo) {

    }

    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(myRecordPresenter !=null)
        {
            myRecordPresenter.detachView();
        }
    }
}
