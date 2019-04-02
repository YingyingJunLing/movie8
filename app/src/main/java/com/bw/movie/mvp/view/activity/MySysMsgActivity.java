package com.bw.movie.mvp.view.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.SysMsgListBean;
import com.bw.movie.mvp.presenter.presenterimpl.SysMsgListPresenter;

import com.bw.movie.mvp.view.adapter.SysMsgListAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

public class MySysMsgActivity extends BaseActivity<Contract.IMySysMsgView,SysMsgListPresenter> implements Contract.IMySysMsgView  {


    private SysMsgListPresenter sysMsgListPresenter;
    private String userId;
    private String sessionId;
    private HashMap<String, String> hashMap;
    private RecyclerView sysMsg_recy;
    public int page =1,count =10;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_my_sys_msg);

    }
    //得到userId    sessionId
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getLoginData(LoginBean.ResultBean resultBean) {
        userId = resultBean.getUserId();
        sessionId = resultBean.getSessionId();
    }
    @Override
    protected void initView() {
        hashMap = new HashMap<>();
        hashMap.put("userId",userId);
        hashMap.put("sessionId",sessionId);
        Log.e("myMessageBean", hashMap +"");
        sysMsg_recy = findViewById(R.id.sysMsg_recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MySysMsgActivity.this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        sysMsg_recy.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected SysMsgListPresenter createPresenter() {
        sysMsgListPresenter = new SysMsgListPresenter();
        return sysMsgListPresenter;
    }

    @Override
    protected void getData() {
        sysMsgListPresenter.onISysMsgPre(hashMap,page,count);

    }
    @Override
    public void onISysMsg(Object o)
    {
        if(o instanceof SysMsgListBean)
        {
            SysMsgListBean sysMsgListBean = (SysMsgListBean) o;
            SysMsgListAdapter sysMsgListAdapter = new SysMsgListAdapter(MySysMsgActivity.this, sysMsgListBean);
            sysMsg_recy.setAdapter(sysMsgListAdapter);
        }

    }
    @Override
    public void onIMySysMsgFail(String errorInfo) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(sysMsgListPresenter!=null)
        {
            sysMsgListPresenter.detachView();
        }
    }
}
