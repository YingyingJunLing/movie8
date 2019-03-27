package com.bw.movie.mvp.view.activity;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bw.movie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.text1)
    TextView text1;
    private AnimatorSet animatorSet;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        sp = getSharedPreferences("start", Context.MODE_PRIVATE);
        if(sp.getBoolean("key", false)){
            Intent intent = new Intent(StartActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        //如果是第一次进 旧存值
        sp.edit().putBoolean("key", true).commit();

//        //平移
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(text, "translationX",text.getTranslationX(), 800);
//        //旋转
//        ObjectAnimator objectAnimator1= ObjectAnimator.ofFloat(text1,"rotation",0,360);
//        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(text1, "scaleX", 0, 1);
//        animatorSet = new AnimatorSet();
//        animatorSet.play(objectAnimator).with(objectAnimator1).with(objectAnimator3);
//        animatorSet.setDuration(5000);
//        animatorSet.start();
        Thread myThread = new Thread() {//创建子线程
            @Override
            public void run() {
                try {
                    sleep(3000);//使程序休眠秒
                    Intent it = new Intent(getApplicationContext(), GuideActivity.class);//启动MainActivity
                    startActivity(it);
                    finish();//关闭当前活动
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程

    }
}
