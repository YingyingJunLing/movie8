package com.bw.movie.mvp.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.presenter.presenterimpl.MyFeedBackPresenter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

public class MyFeedDBActivity extends BaseActivity<Contract.IMyFeedBackView,MyFeedBackPresenter> implements View.OnClickListener ,Contract.IMyFeedBackView{

    private TextView commit;
    private ImageView fan;
    private EditText edit;
    private String s;
    private MyFeedBackPresenter myFeedBackPresenter;
    private String userId;
    private String sessionId;
    private HashMap<String, String> hashMap;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_my_feed_db);

    }

    @Override
    protected void initView() {
        commit = findViewById(R.id.my_feed_commit);
        fan = findViewById(R.id.fan);
        edit = findViewById(R.id.my_feed_edit);
        fan.setOnClickListener(this);
        commit.setOnClickListener(this);
        hashMap = new HashMap<>();
        hashMap.put("userId",userId);
        hashMap.put("sessionId",sessionId);

    }
    //得到userId    sessionId
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getLoginData(LoginBean.ResultBean resultBean) {
        userId = resultBean.getUserId();
        sessionId = resultBean.getSessionId();

    }
    @Override
    protected MyFeedBackPresenter createPresenter() {
        myFeedBackPresenter = new MyFeedBackPresenter();
        return myFeedBackPresenter;
    }

    @Override
    protected void getData() {
        myFeedBackPresenter.onIMyFeedBackPre(hashMap,s);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.my_feed_commit:
                s = edit.getText().toString();
                if(s.equals(""))
                {
                    Toast.makeText(MyFeedDBActivity.this,"输入内容不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MyFeedDBActivity.this, MyFeedDBSuccessActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.fan:
                finish();
                break;
        }
    }

    @Override
    public void onIMyFeedBackSuccess(Object o) {

    }

    @Override
    public void onIMyFeedBackFail(String errorInfo) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
