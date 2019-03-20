package com.bw.movie.mvp.view.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.CinemaListBean;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.presenter.presenterimpl.CinemaListPresenter;
import com.bw.movie.mvp.view.adapter.CinemaListAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CinemaListActivity extends BaseActivity<Contract.ICinemaListView, CinemaListPresenter> implements Contract.ICinemaListView {

    @BindView(R.id.cinema_list_recycle)
    RecyclerView cinemaListRecycle;
    private CinemaListPresenter cinemaListPresenter;
    private int id;
    private String name;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cinema_list);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEvent(MoviesDetailBean.ResultBean resultBean) {
        id = resultBean.getId();
        name = resultBean.getName();
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        cinemaListRecycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected CinemaListPresenter createPresenter() {
        cinemaListPresenter = new CinemaListPresenter();
        return cinemaListPresenter;
    }

    @Override
    protected void getData() {
        cinemaListPresenter.onICinemaListPre(id);
    }

    @Override
    public void onICinemaListSuccess(Object o) {
        Log.i("根据电影ID", o.toString());
        if (o instanceof CinemaListBean) {
            CinemaListBean cinemaListBean = (CinemaListBean) o;
            List<CinemaListBean.ResultBean> result = cinemaListBean.getResult();
            cinemaListRecycle.setAdapter(new CinemaListAdapter(this,result));
        }
    }

    @Override
    public void onICinemaListFail(String errorInfo) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        createPresenter().onDestory();
        EventBus.getDefault().unregister(this);
    }
}
