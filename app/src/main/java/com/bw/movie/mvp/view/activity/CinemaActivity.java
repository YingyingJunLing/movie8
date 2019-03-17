package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.adapter.ViewPagerCinemaAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.frag.Frag_Cinema_Coming;
import com.bw.movie.mvp.view.frag.Frag_Cinema_Hot;
import com.bw.movie.mvp.view.frag.Frag_Cinema_release;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CinemaActivity extends BaseActivity {
    @BindView(R.id.hotmovie)
    RadioButton hotmovie;
    @BindView(R.id.releasemovie)
    RadioButton releasemovie;
    @BindView(R.id.comingsoonmovie)
    RadioButton comingsoonmovie;
    @BindView(R.id.viewPager_cinema)
    ViewPager viewPagerCinema;
    @BindView(R.id.radio_cinema)
    RadioGroup radioCinema;
    private Frag_Cinema_Hot frag_cinema_hot;
    private Frag_Cinema_release frag_cinema_release;
    private Frag_Cinema_Coming frag_cinema_coming;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cinema);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        FragmentManager manager = getSupportFragmentManager();
        final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        frag_cinema_hot = new Frag_Cinema_Hot();
        frag_cinema_release = new Frag_Cinema_release();
        frag_cinema_coming = new Frag_Cinema_Coming();
        fragmentArrayList.add(frag_cinema_hot);
        fragmentArrayList.add(frag_cinema_release);
        fragmentArrayList.add(frag_cinema_coming);
        viewPagerCinema.setAdapter(new ViewPagerCinemaAdapter(manager,fragmentArrayList));
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void getData() {

    }
}
