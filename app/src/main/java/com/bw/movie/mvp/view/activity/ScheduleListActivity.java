package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.CinemaIfoBean;
import com.bw.movie.mvp.model.bean.CinemaListBean;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.model.bean.ScheduleListBean;
import com.bw.movie.mvp.model.utils.NetworkErrorUtils;
import com.bw.movie.mvp.presenter.presenterimpl.ScheduleListPresenter;
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
/**
 * @author zhangbo
 * 影院电影详情
 */
public class ScheduleListActivity extends BaseActivity<Contract.IScheduleListView, ScheduleListPresenter> implements Contract.IScheduleListView {

    @BindView(R.id.cinema_text_name)
    TextView cinemaTextName;
    @BindView(R.id.cinema_text_address)
    TextView cinemaTextAddress;
    @BindView(R.id.schedule_img)
    SimpleDraweeView scheduleImg;
    @BindView(R.id.schedule_recycle)
    RecyclerView scheduleRecycle;
    @BindView(R.id.schedule_list_name)
    TextView scheduleListName;
    @BindView(R.id.schedule_list_type)
    TextView scheduleListType;
    @BindView(R.id.schedule_list_director)
    TextView scheduleListDirector;
    @BindView(R.id.schedule_list_time)
    TextView scheduleListTime;
    @BindView(R.id.schedule_list_address)
    TextView scheduleListAddress;
    private ScheduleListPresenter scheduleListPresenter;
    private int mid;
    private int cid;
    private NetworkErrorUtils networkErrorUtils;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_schedule_list);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEvent(MoviesDetailBean.ResultBean resultBean) {
        mid = resultBean.getId();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void getCinema(CinemaListBean.ResultBean resultBean) {
        cid = resultBean.getId();
    }

    @Override
    protected void initView() {
        networkErrorUtils = new NetworkErrorUtils(ScheduleListActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        scheduleRecycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected ScheduleListPresenter createPresenter() {
        scheduleListPresenter = new ScheduleListPresenter();
        return scheduleListPresenter;
    }

    @Override
    protected void getData() {

        scheduleListPresenter.onIScheduleListCinemaPre(cid);
        Log.i("影院ID", cid + "");
    }

    @Override
    public void onIScheduleListSuccess(Object o) {
        Log.i("电影ID和影院ID", o.toString());
        if (o instanceof ScheduleListBean) {
            ScheduleListBean scheduleListBean = (ScheduleListBean) o;
            List<ScheduleListBean.ResultBean> result = scheduleListBean.getResult();
            scheduleRecycle.setAdapter(new ScheuleAdapter(this, result));
        }
    }

    @Override
    public void onIScheduleListCinemaSuccess(Object o) {
        if (o instanceof CinemaIfoBean) {
            CinemaIfoBean cinemaIfoBean = (CinemaIfoBean) o;
            CinemaIfoBean.ResultBean result = cinemaIfoBean.getResult();
            scheduleListPresenter.onIScheduleListMoviePre(mid);
            cinemaTextName.setText(result.getName());
            cinemaTextAddress.setText(result.getAddress());
        }
    }

    @Override
    public void onIScheduleListMovieSuccess(Object o) {
        if (o instanceof MoviesDetailBean) {
            MoviesDetailBean moviesDetailBean = (MoviesDetailBean) o;
            MoviesDetailBean.ResultBean result = moviesDetailBean.getResult();
            FrescoUtils.setPic(result.getImageUrl(), scheduleImg);
            scheduleListName.setText(result.getName());
            scheduleListType.setText("类型: "+result.getMovieTypes());
            scheduleListDirector.setText("导演: "+result.getDirector());
            scheduleListTime.setText("时长: "+result.getFollowMovie()+"分钟");
            scheduleListTime.setText("产地: "+result.getPlaceOrigin());
            scheduleListPresenter.onIScheduleListPre(cid, mid);
        }
    }

    @Override
    public void onIScheduleListFail(String errorInfo) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scheduleListPresenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
