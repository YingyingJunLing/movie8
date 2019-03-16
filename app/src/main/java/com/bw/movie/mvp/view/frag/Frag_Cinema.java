package com.bw.movie.mvp.view.frag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.ComingSoonMovieBean;
import com.bw.movie.mvp.model.bean.HotMovieBean;
import com.bw.movie.mvp.model.bean.ReleaseMovieBean;
import com.bw.movie.mvp.presenter.presenterimpl.CinemaPresenter;
import com.bw.movie.mvp.view.adapter.CinemaRecycleAdapter;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author zhangbo
 */
public class Frag_Cinema extends BaseFragment<Contract.ICinemaView,CinemaPresenter> implements Contract.ICinemaView{

    private View view;
    private RecyclerView frag_cinema_recycle;
    private CinemaPresenter cinemaPresenter;
    private HotMovieBean hotMovieBean;
    private ReleaseMovieBean releaseMovieBean;
    private ComingSoonMovieBean comingSoonMovieBean;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = View.inflate(getActivity(), R.layout.frag_cinema, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        frag_cinema_recycle = view.findViewById(R.id.frag_cinema_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        frag_cinema_recycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        cinemaPresenter.onICinemaPre(1,10);
    }

    @Override
    protected CinemaPresenter createPresenter() {
        cinemaPresenter = new CinemaPresenter();
        return cinemaPresenter;
    }

    @Override
    public void onICinemaSuccess(Object o) {
        if (o instanceof HotMovieBean){
            hotMovieBean = (HotMovieBean) o;
            cinemaPresenter.onIReleaseMovie(1,10);
        }
    }

    @Override
    public void onIReleaseMovieSuccess(Object o) {
        if (o instanceof ReleaseMovieBean){
            releaseMovieBean = (ReleaseMovieBean) o;
            cinemaPresenter.onIComingSoonMovie(1,10);
        }
    }

    @Override
    public void onIComingSoonMovieSuccess(Object o) {
        if (o instanceof ComingSoonMovieBean){
            comingSoonMovieBean = (ComingSoonMovieBean) o;
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("0",hotMovieBean);
            hashMap.put("1",hotMovieBean);
            hashMap.put("2",releaseMovieBean);
            hashMap.put("3",comingSoonMovieBean);
            CinemaRecycleAdapter cinemaRecycleAdapter = new CinemaRecycleAdapter(getActivity(),hashMap);
            frag_cinema_recycle.setAdapter(cinemaRecycleAdapter);
        }
    }

    @Override
    public void onICinemaFail(String errorInfo) {

    }
}
