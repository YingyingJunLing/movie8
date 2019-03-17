package com.bw.movie.mvp.view.frag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.ReleaseMovieBean;
import com.bw.movie.mvp.presenter.presenterimpl.CinemaPresenter;
import com.bw.movie.mvp.view.adapter.CinemaReleaseAdapter;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class Frag_Cinema_release extends BaseFragment<Contract.ICinemaView,CinemaPresenter> implements Contract.ICinemaView {

    private View view;
    private CinemaPresenter cinemaPresenter;
    private XRecyclerView cinema_release_recycle;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = View.inflate(getActivity(), R.layout.frag_cinema_release, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        cinema_release_recycle = view.findViewById(R.id.cinema_release_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        cinema_release_recycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        cinemaPresenter.onIReleaseMovie(1,10);
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
        if (o instanceof ReleaseMovieBean){
            ReleaseMovieBean releaseMovieBean = (ReleaseMovieBean) o;
            List<ReleaseMovieBean.ResultBean> result = releaseMovieBean.getResult();
            cinema_release_recycle.setAdapter(new CinemaReleaseAdapter(getActivity(),result));
        }
    }

    @Override
    public void onIComingSoonMovieSuccess(Object o) {

    }

    @Override
    public void onICinemaFail(String errorInfo) {

    }
}
