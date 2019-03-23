package com.bw.movie.mvp.view.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.ComingSoonMovieBean;
import com.bw.movie.mvp.model.bean.HotMovieBean;
import com.bw.movie.mvp.model.bean.LocationBean;
import com.bw.movie.mvp.model.bean.RecommendMovieBean;
import com.bw.movie.mvp.presenter.presenterimpl.CinemaPresenter;
import com.bw.movie.mvp.view.activity.LocationActivity;
import com.bw.movie.mvp.view.adapter.CinemaRecycleAdapter;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author zhangbo
 */
public class Frag_Cinema extends BaseFragment<Contract.ICinemaView, CinemaPresenter> implements Contract.ICinemaView {

    private View view;
    private RecyclerView frag_cinema_recycle;
    private CinemaPresenter cinemaPresenter;
    private HotMovieBean hotMovieBean;
    private RecommendMovieBean recommendMovieBean;
    private ComingSoonMovieBean comingSoonMovieBean;
    private ImageView loactionImg;
    private TextView textAddress;
    private RecyclerView fragCinemaRecycle;
    private String city;

    /**
     * 初始化布局
     *
     * @param inflater
     * @return
     */
    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        EventBus.getDefault().register(this);
        view = View.inflate(getActivity(), R.layout.frag_cinema, null);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLocation(LocationBean locationBean){
        city = locationBean.getCity();
    }

    /**
     * 初始化控件
     *
     * @param view 打气筒生成的View对象
     */
    @Override
    protected void initFragmentChildView(View view) {

        frag_cinema_recycle = view.findViewById(R.id.frag_cinema_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        frag_cinema_recycle.setLayoutManager(linearLayoutManager);
        textAddress = view.findViewById(R.id.text_address);
        loactionImg = view.findViewById(R.id.loaction_img);
        loactionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LocationActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (city==null){
            textAddress.setText("定位中...");
        }else {
            textAddress.setText(city);
        }
    }

    /**
     * j加载数据
     *
     * @param savedInstanceState
     */
    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        cinemaPresenter.onICinemaPre(1, 10);
    }

    /**
     * 返回p层
     *
     * @return
     */
    @Override
    protected CinemaPresenter createPresenter() {
        cinemaPresenter = new CinemaPresenter();
        return cinemaPresenter;
    }

    /**
     * 热门电影
     *
     * @param o
     */
    @Override
    public void onICinemaSuccess(Object o) {
        if (o instanceof HotMovieBean) {
            hotMovieBean = (HotMovieBean) o;
            cinemaPresenter.onIReleaseMovie(1, 10);
        }
    }

    /**
     * 正在上映
     *
     * @param o
     */

    @Override
    public void onIReleaseMovieSuccess(Object o) {
        if (o instanceof RecommendMovieBean) {
            recommendMovieBean = (RecommendMovieBean) o;
            cinemaPresenter.onIComingSoonMovie(1, 10);
        }
    }

    /**
     * 即将上映
     *
     * @param o
     */
    @Override
    public void onIComingSoonMovieSuccess(Object o) {
        if (o instanceof ComingSoonMovieBean) {
            comingSoonMovieBean = (ComingSoonMovieBean) o;
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("0", hotMovieBean);
            hashMap.put("1", hotMovieBean);
            hashMap.put("2", recommendMovieBean);
            hashMap.put("3", comingSoonMovieBean);
            CinemaRecycleAdapter cinemaRecycleAdapter = new CinemaRecycleAdapter(getActivity(), hashMap);
            frag_cinema_recycle.setAdapter(cinemaRecycleAdapter);
        }
    }

    @Override
    public void onICinemaFail(String errorInfo) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        cinemaPresenter.onDestory();
    }

}
