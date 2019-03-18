package com.bw.movie.mvp.view.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.mvp.presenter.presenterimpl.FilmPresenter;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;

/**
 * @author zhangbo
 * 影院
 */
public class Frag_Film extends BaseFragment<Contract.IFilmView,FilmPresenter> implements Contract.IFilmModel {
    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        View view = View.inflate(getActivity(), R.layout.frag_film, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {

    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected FilmPresenter createPresenter() {
        return null;
    }

    @Override
    public void ICFilm(String url) {

    }
}
