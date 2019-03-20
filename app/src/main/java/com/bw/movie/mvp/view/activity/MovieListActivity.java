package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.CinemaIfoBean;
import com.bw.movie.mvp.model.bean.CinemaListBean;
import com.bw.movie.mvp.model.bean.MovieListBean;
import com.bw.movie.mvp.model.bean.ScheduleListBean;
import com.bw.movie.mvp.presenter.presenterimpl.MovieListPresenter;
import com.bw.movie.mvp.view.adapter.MovieListCoverFlowAdapter;
import com.bw.movie.mvp.view.adapter.ScheuleAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

public class MovieListActivity extends BaseActivity<Contract.IMovieListView, MovieListPresenter> implements Contract.IMovieListView {

    @BindView(R.id.movie_list_logo)
    SimpleDraweeView movieListLogo;
    @BindView(R.id.movie_list_name)
    TextView movieListName;
    @BindView(R.id.movie_list_address)
    TextView movieListAddress;
    @BindView(R.id.movie_list_cover_flow)
    RecyclerCoverFlow movieListCoverFlow;
    @BindView(R.id.movie_list_recycle)
    RecyclerView movieListRecycle;
    private MovieListPresenter movieListPresenter;
    private int cid;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void getCinema(CinemaListBean.ResultBean resultBean) {
        cid = resultBean.getId();
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        movieListRecycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected MovieListPresenter createPresenter() {
        movieListPresenter = new MovieListPresenter();
        return movieListPresenter;
    }

    @Override
    protected void getData() {
        movieListPresenter.onIMovieListCinemaPre(cid);
    }

    @Override
    public void onIMovieListSuccess(Object o) {
        if (o instanceof MovieListBean) {
            MovieListBean movieListBean = (MovieListBean) o;
            if (movieListBean.getStatus().equals("0000")) {
                final List<MovieListBean.ResultBean> result = movieListBean.getResult();
                MovieListCoverFlowAdapter movieListCoverFlowAdapter = new MovieListCoverFlowAdapter(this, result);
                movieListCoverFlow.setAdapter(movieListCoverFlowAdapter);
                int mid = result.get(0).getId();
                movieListPresenter.onIMovieListCinemaMoviePre(cid, mid);
                movieListCoverFlow.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);

                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                    }
                });

            }
        }
    }

    @Override
    public void onIMovieListCinemaSuccess(Object o) {
        if (o instanceof CinemaIfoBean) {
            CinemaIfoBean cinemaIfoBean = (CinemaIfoBean) o;
            CinemaIfoBean.ResultBean result = cinemaIfoBean.getResult();
            FrescoUtils.setPic(result.getLogo(), movieListLogo);
            movieListName.setText(result.getName());
            movieListAddress.setText(result.getAddress());
        }
        movieListPresenter.onIMovieListPre(cid);
    }

    @Override
    public void onIMovieListCinemaMovieSuccess(Object o) {
        Log.i("相关档期", o.toString());
        if (o instanceof ScheduleListBean) {
            ScheduleListBean scheduleListBean = (ScheduleListBean) o;
            if (scheduleListBean.getMessage().equals("0000")) {
                List<ScheduleListBean.ResultBean> result = scheduleListBean.getResult();
                movieListRecycle.setAdapter(new ScheuleAdapter(this, result));
            }
        }
    }

    @Override
    public void onIMovieListFail(String errorInfo) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
