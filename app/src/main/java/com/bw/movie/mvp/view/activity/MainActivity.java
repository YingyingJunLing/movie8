package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.frag.Frag_Cinema;
import com.bw.movie.mvp.view.frag.Frag_Film;
import com.bw.movie.mvp.view.frag.Frag_My;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
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
    private FragmentManager manager;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        cinemaBtn.setChecked(true);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        frag_cinema = new Frag_Cinema();
        frag_film = new Frag_Film();
        frag_my = new Frag_My();
        transaction.add(R.id.main_fragment,frag_cinema);
        transaction.add(R.id.main_fragment,frag_film);
        transaction.add(R.id.main_fragment,frag_my);
        transaction.show(frag_cinema).hide(frag_film).hide(frag_my);
        transaction.commit();
        redioRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.cinema_btn:
                        transaction.show(frag_cinema).hide(frag_film).hide(frag_my);
                        break;
                    case R.id.film_btn:
                        transaction.show(frag_film).hide(frag_cinema).hide(frag_my);
                        break;
                    case R.id.my_btn:
                        transaction.show(frag_my).hide(frag_film).hide(frag_cinema);
                        break;
                }
                transaction.commit();
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
