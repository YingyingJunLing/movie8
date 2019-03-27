package com.bw.movie.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

/**
 * @author zhangbo
 * 影片分类
 */
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
    private int type;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cinema);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        //判断影片类型
        Intent intent = getIntent();
        type = intent.getIntExtra("1",1);
        Log.i("type判断类型",type+"");
        FragmentManager manager = getSupportFragmentManager();
        final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        frag_cinema_hot = new Frag_Cinema_Hot();
        frag_cinema_release = new Frag_Cinema_release();
        frag_cinema_coming = new Frag_Cinema_Coming();
        fragmentArrayList.add(frag_cinema_hot);
        fragmentArrayList.add(frag_cinema_release);
        fragmentArrayList.add(frag_cinema_coming);
       Button fan =  findViewById(R.id.fan);
       fan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
        viewPagerCinema.setAdapter(new ViewPagerCinemaAdapter(manager,fragmentArrayList));
        if (type==1){
            viewPagerCinema.setCurrentItem(0);
            hotmovie.setChecked(true);
        }else if (type==2){
            viewPagerCinema.setCurrentItem(1);
            releasemovie.setChecked(true);
        }else {
            viewPagerCinema.setCurrentItem(2);
            comingsoonmovie.setChecked(true);
        }
        radioCinema.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.hotmovie:
                        viewPagerCinema.setCurrentItem(0);
                        break;
                    case R.id.releasemovie:
                        viewPagerCinema.setCurrentItem(1);
                        break;
                    case R.id.comingsoonmovie:
                        viewPagerCinema.setCurrentItem(2);
                        break;
                }
            }
        });
        viewPagerCinema.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        hotmovie.setChecked(true);
                        break;
                    case 1:
                        releasemovie.setChecked(true);
                        break;
                    case 2:
                        comingsoonmovie.setChecked(true);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
