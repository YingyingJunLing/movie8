package com.bw.movie.mvp.view.frag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.FindNearCinemaBean;
import com.bw.movie.mvp.presenter.presenterimpl.NearPresenter;
import com.bw.movie.mvp.view.adapter.NearAdapter;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class Frag_Film_Near extends BaseFragment<Contract.INearView,NearPresenter> implements Contract.INearView {

    private View view;
    private NearPresenter nearPresenter;
    private XRecyclerView near_recycle;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = View.inflate(getActivity(), R.layout.frag_film_near, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        near_recycle = view.findViewById(R.id.near_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        near_recycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        nearPresenter.onINearPre(1,10);
    }

    @Override
    protected NearPresenter createPresenter() {
        nearPresenter = new NearPresenter();
        return nearPresenter;
    }

    @Override
    public void onINearSuccess(Object o) {
        if (o instanceof FindNearCinemaBean){
            FindNearCinemaBean findNearCinemaBean = (FindNearCinemaBean) o;
            List<FindNearCinemaBean.ResultBean> result = findNearCinemaBean.getResult();
            near_recycle.setAdapter(new NearAdapter(getActivity(),result));
        }
    }

    @Override
    public void onINearFail(String errorInfo) {

    }
}
