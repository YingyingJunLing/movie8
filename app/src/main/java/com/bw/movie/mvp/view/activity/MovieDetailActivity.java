package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.presenter.presenterimpl.MovieDetailPresenter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseActivity<Contract.IMovieDetailView, MovieDetailPresenter> implements Contract.IMovieDetailView {

    @BindView(R.id.movie_detail_text)
    TextView movieDetailText;
    @BindView(R.id.movie_detail_img)
    SimpleDraweeView movieDetailImg;
    private MovieDetailPresenter movieDetailPresenter;
    private int id;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEvent(MoviesDetailBean.ResultBean resultBean) {
        id = resultBean.getId();
    }

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected MovieDetailPresenter createPresenter() {
        movieDetailPresenter = new MovieDetailPresenter();
        return movieDetailPresenter;
    }

    @Override
    protected void getData() {
        movieDetailPresenter.onIMovieDetailPre(id);
    }

    @Override
    public void onIMovieDetailSuccess(Object o) {
        Log.i("详情", o.toString());
        if (o instanceof MoviesDetailBean){
            MoviesDetailBean moviesDetailBean = (MoviesDetailBean) o;
            MoviesDetailBean.ResultBean result = moviesDetailBean.getResult();
            Log.i("详情名字",result.getName());
            movieDetailText.setText(result.getName());
            FrescoUtils.setPic(result.getImageUrl(),movieDetailImg);
        }
    }

    @Override
    public void onIMovieDetailFail(String errorInfo) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
