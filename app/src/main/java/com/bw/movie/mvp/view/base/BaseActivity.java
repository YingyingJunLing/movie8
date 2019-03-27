package com.bw.movie.mvp.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.mvp.model.utils.NetworkUtils;
import com.bw.movie.mvp.presenter.base.BasePresenter;

import me.jessyan.autosize.internal.CustomAdapt;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements CustomAdapt {
    public String TAG = getClass().getSimpleName() + "";

    protected T mPresenter;

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityView(savedInstanceState);
        mContext = BaseActivity.this;

        //创建presenter
        mPresenter = createPresenter();

        // presenter与view绑定
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }

        NetworkUtils networkUtils = new NetworkUtils();
        networkUtils.NetworkDynamicJudgment(this);
        initView();
        if (networkUtils.isNetworkConnected(this)){
            getData();
        }

    }


    /**
     * 关于Activity的界面填充的抽象方法，需要子类必须实现
     */
    protected abstract void initActivityView(Bundle savedInstanceState);

    /**
     * 加载页面元素
     */
    protected abstract void initView();

    /**
     * 创建Presenter 对象
     *
     * @return
     */
    protected abstract T createPresenter();

    protected abstract void getData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 667;
    }

}
