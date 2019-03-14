package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());

        initView();
        initData();
        bindEvent();

    }

    protected abstract void bindEvent();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int bindLayout();

    public void ToastData(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}
