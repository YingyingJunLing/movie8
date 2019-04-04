package com.bw.movie.mvp.view.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bw.movie.R;
import com.bw.movie.mvp.view.frag.FragmentGuideFour;
import com.bw.movie.mvp.view.frag.FragmentGuideOne;
import com.bw.movie.mvp.view.frag.FragmentGuideThree;
import com.bw.movie.mvp.view.frag.FragmentGuideTwo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity  {


    private ViewPager id_view_pager;

    private List<Fragment> fragmentList;

    private ImageView[] imageViews;

    private LinearLayout linearLayout;

    private int currentIndicator = 0;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //getSupportActionBar().hide();
        initView();
        initData();
        sp = getSharedPreferences("guide", Context.MODE_PRIVATE);
        if(sp.getBoolean("key", false)){
            Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        //如果是第一次进 旧存值
        sp.edit().putBoolean("key", true).commit();

    }

    private void initData() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new FragmentGuideOne());
        fragmentList.add(new FragmentGuideTwo());
        fragmentList.add(new FragmentGuideThree());
        fragmentList.add(new FragmentGuideFour());
        id_view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        int len = fragmentList.size();
        imageViews = new ImageView[len];
        for (int i = 0; i < len; i++) {
            imageViews[i] = (ImageView) linearLayout.getChildAt(i);
            imageViews[i].setBackgroundResource(R.drawable.image_my_car_selected);
        }
        imageViews[currentIndicator].setBackgroundResource(R.drawable.image_my_car_normal);
        id_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imageViews[position].setBackgroundResource(R.drawable.image_my_car_normal);
                imageViews[currentIndicator].setBackgroundResource(R.drawable.image_my_car_selected);
                currentIndicator = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        id_view_pager = (ViewPager) findViewById(R.id.id_view_pager);
        linearLayout = (LinearLayout) findViewById(R.id.id_linear_view_pager);
    }

}

