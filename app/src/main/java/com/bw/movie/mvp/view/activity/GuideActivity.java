package com.bw.movie.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bw.movie.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;

    RadioGroup radioGroup;
    @BindView(R.id.jump)
    Button jump;
    private ArrayList<ImageView> imageView;
    private ImageView img;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        radioGroup = findViewById(R.id.radioGroup);
        sp = getSharedPreferences("guide", Context.MODE_PRIVATE);
        if(sp.getBoolean("key", false)){
            Intent intent = new Intent(GuideActivity.this,GuideActivity.class);
            startActivity(intent);
        }
        //如果是第一次进 旧存值
        sp.edit().putBoolean("key", true).commit();
        //设置按钮隐藏
        jump.setVisibility(View.GONE);
        //按钮点击事件
        jump.setOnClickListener(this);
        // 4：viewpage数据
        int[] arr = {R.mipmap.yi, R.mipmap.er, R.mipmap.san,
                R.mipmap.si};
        //5：新建list集合
        imageView = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            img = new ImageView(GuideActivity.this);
            img.setImageResource(arr[i]);
            imageView.add(img);
        }
        pager.setAdapter(new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return imageView.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                ImageView view = imageView.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView((View) object);
            }
        });

        //9:创建单选框
        for (int i = 0; i < arr.length; i++) {
            RadioButton button = new RadioButton(GuideActivity.this);
            //给button设置选择器
            button.setButtonDrawable(R.drawable.btn);
            //增加动态选择器
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //设置左边距
            params.leftMargin = 8;
            //把边距和按钮添加到一起
            radioGroup.addView(button, params);
        }
        //默认第一个被选中
        radioGroup.check(radioGroup.getChildAt(0).getId());
        text1.setText("一场电影");
        text2.setText("洗净你的灵魂");
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                radioGroup.check(radioGroup.getChildAt(arg0).getId());
                if (arg0 == 0) {
                    text1.setText("一场电影");
                    text2.setText("洗净你的灵魂");
                }
                if (arg0 == 1) {
                    text1.setText("一场电影");
                    text2.setText("看遍人生百态");
                }
                if (arg0 == 2) {
                    text1.setText("一场电影");
                    text2.setText("荡涤你的心灵");
                }
                if (arg0 == 3) {
                    jump.setVisibility(View.VISIBLE);
                    text1.setText("八维移动通信学院作品");
                    text2.setText("带您开启一段美好的生命之旅");
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.jump:
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("不是第一次", false);
                edit.commit();
                //14：跳转
                startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                finish();
                break;
        }
    }
}
