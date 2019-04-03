package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


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
                        MobclickAgent.onEvent(MainActivity.this, "cinema_btn");//参数二为当前统计的事件ID
                        transaction.show(frag_cinema).hide(frag_film).hide(frag_my);
                        break;
                    case R.id.film_btn:
                        MobclickAgent.onEvent(MainActivity.this, "film_btn");//参数二为当前统计的事件ID
                        transaction.show(frag_film).hide(frag_cinema).hide(frag_my);
                        break;
                    case R.id.my_btn:
                        MobclickAgent.onEvent(MainActivity.this, "my_btn");//参数二为当前统计的事件ID
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
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
}
