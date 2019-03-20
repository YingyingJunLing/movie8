package com.bw.movie.mvp.view.frag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.RecommendCinemaBean;
import com.bw.movie.mvp.presenter.presenterimpl.FilmRecommendPresenter;
import com.bw.movie.mvp.view.adapter.RecommendAdapter;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class Frag_Film_Recommend extends BaseFragment<Contract.IRecommendView,FilmRecommendPresenter> implements Contract.IRecommendView {

    private View view;
    private FilmRecommendPresenter filmRecommendPresenter;
    private XRecyclerView recommend_recycle;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = View.inflate(getActivity(), R.layout.frag_film_recommend, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        recommend_recycle = view.findViewById(R.id.recommend_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recommend_recycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        filmRecommendPresenter.onIRecommendPre(1,10);
    }

    @Override
    protected FilmRecommendPresenter createPresenter() {
        filmRecommendPresenter = new FilmRecommendPresenter();
        return filmRecommendPresenter;
    }

    @Override
    public void onIRecommendSuccess(Object o) {
        Log.i("推荐影院fragment",o.toString());
        if (o instanceof RecommendCinemaBean){
            RecommendCinemaBean recommendCinemaBean = (RecommendCinemaBean) o;
            List<RecommendCinemaBean.ResultBean> result = recommendCinemaBean.getResult();
            recommend_recycle.setAdapter(new RecommendAdapter(getActivity(),result));
        }
    }

    @Override
    public void onIRecommendFail(String errorInfo) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        filmRecommendPresenter.onDestroy();
    }
}
