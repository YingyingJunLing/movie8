package com.bw.movie.mvp.view.base;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.mvp.model.utils.NetworkUtils;
import com.bw.movie.mvp.presenter.base.BasePresenter;

import me.jessyan.autosize.internal.CustomAdapt;

import static com.bw.movie.mvp.model.utils.NoStudoInterent.connectionReceiver;

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
        connectionReceiver = connectionReceiver;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionReceiver, intentFilter);
        // presenter与view绑定
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
        //网络动态权限
        stateNetWork();

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
    //动态注册权限
    private void stateNetWork() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            String[] mStatenetwork = new String[]{
                    //写的权限
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    //读的权限
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    //入网权限
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    //WIFI权限
                    Manifest.permission.ACCESS_WIFI_STATE,
                    //读手机权限
                    Manifest.permission.READ_PHONE_STATE,
                    //网络权限
                    Manifest.permission.INTERNET,
                    //相机
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_APN_SETTINGS,
                    Manifest.permission.ACCESS_NETWORK_STATE,
            };
            ActivityCompat.requestPermissions(this,mStatenetwork,100);
        }
    }

}
