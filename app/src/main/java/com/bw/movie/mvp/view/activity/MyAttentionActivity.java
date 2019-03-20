package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.AttentionCamera;

import com.bw.movie.mvp.model.bean.AttentionMovie;
import com.bw.movie.mvp.model.bean.LoginBean;

import com.bw.movie.mvp.presenter.presenterimpl.MyAttentionPresenter;
import com.bw.movie.mvp.view.adapter.AttentionCameraAdapter;
import com.bw.movie.mvp.view.adapter.AttentionFilmAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAttentionActivity extends BaseActivity<Contract.IAttentionView,MyAttentionPresenter> implements Contract.IAttentionView, View.OnClickListener {

    @BindView(R.id.my_attention_movie)
    Button myAttentionMovie;
    @BindView(R.id.my_attention_camera)
    Button myAttentionCamera;
    @BindView(R.id.my_attention_movie_rec)
    RecyclerView myAttentionMovieRec;
    @BindView(R.id.my_attention_camera_rec)
    RecyclerView myAttentionCameraRec;
    private MyAttentionPresenter myAttentionFilmPresenter;
    public int page =1;
    public int count =10;
    private HashMap<String, String> hashMap;
    private HashMap<String, Integer> map;
    private String userId;
    private String sessionId;
    private AttentionFilmAdapter attentionFilmAdapter;
    private AttentionCameraAdapter attentionCameraAdapter;


    @Override
    protected void initActivityView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_my_attention);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getLoginData(LoginBean.ResultBean resultBean) {
        userId = resultBean.getUserId();
        sessionId = resultBean.getSessionId();

    }

    @Override
    protected void initView()
    {
        initLinear();
        myAttentionCameraRec.setVisibility(View.GONE);
        myAttentionMovieRec.setVisibility(View.VISIBLE);
        myAttentionCamera.setOnClickListener(this);
        myAttentionMovie.setOnClickListener(this);
        hashMap = new HashMap<>();
        hashMap.put("userId",userId);
        hashMap.put("sessionId",sessionId);
        Log.e("map",hashMap+"");


    }

    private void initLinear() {
        //影片
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyAttentionActivity.this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        myAttentionMovieRec.setLayoutManager(linearLayoutManager);


        //影院
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MyAttentionActivity.this);
        linearLayoutManager1.setOrientation(OrientationHelper.VERTICAL);
        myAttentionCameraRec.setLayoutManager(linearLayoutManager1);


    }

    @Override
    protected MyAttentionPresenter createPresenter()
    {
        myAttentionFilmPresenter = new MyAttentionPresenter();
        return myAttentionFilmPresenter;
    }

    @Override
    protected void getData()
    {
        myAttentionFilmPresenter.onIAttentionMoviePre(hashMap,page,count);
    }

    @Override
    public void onIAttentionSuccess(Object o)
    {
           if(o instanceof AttentionMovie)
           {
               AttentionMovie attentionFilm = (AttentionMovie) o;
               Log.e("attentionFilm",attentionFilm.toString());
               attentionFilmAdapter = new AttentionFilmAdapter(MyAttentionActivity.this, attentionFilm);
               myAttentionMovieRec.setAdapter(attentionFilmAdapter);
           }
           if(o instanceof AttentionCamera)
           {
               AttentionCamera attentionCamera = (AttentionCamera) o;
               attentionCameraAdapter = new AttentionCameraAdapter(MyAttentionActivity.this, attentionCamera);
               myAttentionCameraRec.setAdapter(attentionCameraAdapter);
           }

    }

    @Override
    public void onIMyFail(String errorInfo)
    {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.my_attention_movie:
                myAttentionCameraRec.setVisibility(View.GONE);
                myAttentionMovieRec.setVisibility(View.VISIBLE);
                break;
            case R.id.my_attention_camera:
                myAttentionMovieRec.setVisibility(View.GONE);
                myAttentionCameraRec.setVisibility(View.VISIBLE);
                myAttentionFilmPresenter.onIAttentionCameraPres(hashMap,page,count);

                break;

        }
    }
}
