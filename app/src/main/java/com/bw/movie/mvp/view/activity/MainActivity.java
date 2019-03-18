package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.adapter.MainViewPagerAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.frag.Frag_Cinema;
import com.bw.movie.mvp.view.frag.Frag_Film;
import com.bw.movie.mvp.view.frag.Frag_My;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.cinema_btn)
    RadioButton cinemaBtn;
    @BindView(R.id.film_btn)
    RadioButton filmBtn;
    @BindView(R.id.my_btn)
    RadioButton myBtn;
    @BindView(R.id.redioRadio)
    RadioGroup redioRadio;
    private Frag_Cinema frag_cinema;
    private Frag_Film frag_film;
    private Frag_My frag_my;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        cinemaBtn.setChecked(true);
        FragmentManager manager = getSupportFragmentManager();
        final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        frag_cinema = new Frag_Cinema();
        frag_film = new Frag_Film();
        frag_my = new Frag_My();
        fragmentArrayList.add(frag_cinema);
        fragmentArrayList.add(frag_film);
        fragmentArrayList.add(frag_my);
        viewPager.setAdapter(new MainViewPagerAdapter(manager,fragmentArrayList));
        redioRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.cinema_btn:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.film_btn:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.my_btn:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        cinemaBtn.setChecked(true);
                        break;
                    case 1:
                        filmBtn.setChecked(true);
                        break;
                    case 2:
                        myBtn.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void getData() {

    }
}
