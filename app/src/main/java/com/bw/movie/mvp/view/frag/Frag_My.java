package com.bw.movie.mvp.view.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.mvp.presenter.presenterimpl.MyPresenter;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;

public class Frag_My extends BaseFragment<Contract.IMyView,MyPresenter> implements Contract.IMyModel {
    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        View view = View.inflate(getActivity(), R.layout.frag_my, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {

    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected MyPresenter createPresenter() {
        return null;
    }

    @Override
    public void IMy(String url) {

    }
}
