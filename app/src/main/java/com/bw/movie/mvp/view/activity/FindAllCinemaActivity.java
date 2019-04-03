package com.bw.movie.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.FindAllCinemasBean;
import com.bw.movie.mvp.presenter.presenterimpl.FindAllPresenter;
import com.bw.movie.mvp.view.adapter.FindAllCinemaAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindAllCinemaActivity extends BaseActivity<Contract.IFindAllView, FindAllPresenter> implements Contract.IFindAllView {

    private XRecyclerView findAllXrecycle;
    private FindAllPresenter findAllPresenter;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find_all_cinema);
    }

    @Override
    protected void initView() {
        findAllXrecycle = findViewById(R.id.find_all_xrecycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        findAllXrecycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected FindAllPresenter createPresenter() {
        findAllPresenter = new FindAllPresenter();
        return findAllPresenter;
    }

    @Override
    protected void getData() {
        String edit = new Intent().getStringExtra("edit");
        findAllPresenter.onIFindAllPre(1, 10, edit);
    }

    @Override
    public void onIFindAllSuccess(Object o) {
        if (o instanceof FindAllCinemasBean){
            FindAllCinemasBean findAllCinemasBean = (FindAllCinemasBean) o;
            List<FindAllCinemasBean.ResultBean> result = findAllCinemasBean.getResult();
            findAllXrecycle.setAdapter(new FindAllCinemaAdapter(FindAllCinemaActivity.this,result));
        }
    }

    @Override
    public void onIFindAllFail(String errorInfo) {

    }
}
