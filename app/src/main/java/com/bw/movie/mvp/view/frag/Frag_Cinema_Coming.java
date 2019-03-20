package com.bw.movie.mvp.view.frag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.ComingSoonMovieBean;
import com.bw.movie.mvp.presenter.presenterimpl.CinemaPresenter;
import com.bw.movie.mvp.view.adapter.CinemaComingAdapter;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class Frag_Cinema_Coming extends BaseFragment<Contract.ICinemaView,CinemaPresenter> implements Contract.ICinemaView {

    private View view;
    private CinemaPresenter cinemaPresenter;
    private XRecyclerView cinema_coming_recycle;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = View.inflate(getActivity(), R.layout.frag_cinema_coming, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        cinema_coming_recycle = view.findViewById(R.id.cinema_coming_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        cinema_coming_recycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        cinemaPresenter.onIComingSoonMovie(1,10);
    }

    @Override
    protected CinemaPresenter createPresenter() {
        cinemaPresenter = new CinemaPresenter();
        return cinemaPresenter;
    }

    @Override
    public void onICinemaSuccess(Object o) {

    }

    @Override
    public void onIReleaseMovieSuccess(Object o) {

    }

    @Override
    public void onIComingSoonMovieSuccess(Object o) {
        if (o instanceof ComingSoonMovieBean){
            ComingSoonMovieBean comingSoonMovieBean = (ComingSoonMovieBean) o;
            List<ComingSoonMovieBean.ResultBean> result = comingSoonMovieBean.getResult();
            cinema_coming_recycle.setAdapter(new CinemaComingAdapter(getActivity(),result));
        }
    }

    @Override
    public void onICinemaFail(String errorInfo) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cinemaPresenter.onDestory();
    }
}
