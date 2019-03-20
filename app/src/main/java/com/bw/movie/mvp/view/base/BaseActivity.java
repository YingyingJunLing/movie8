package com.bw.movie.mvp.view.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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

        initView();

        getData();

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

    public void NetworkDynamicJudgment(final Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.requestNetwork(new NetworkRequest.Builder().build(), new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    Log.i("connect", "onAvailable: 获取到网络连接");
//                    Toast.makeText(context, "获取到网络连接", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onUnavailable() {
                    super.onUnavailable();
                    Log.i("connect", "onUnavailable: 没有获取到网络连接");
                    Toast.makeText(context, "没有获取到网络连接", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLost(Network network) {
                    super.onLost(network);
                    Log.i("connect", "onLost: 当框架严重丢失网络或优雅故障结束时调用");
                    Toast.makeText(context, "网络严重丢失", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    Log.i("connect", "onCapabilitiesChanged: 当为此请求连接的框架所在的网络更改功能但仍满足所述需求时调用");
                }

                @Override
                public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                    super.onLinkPropertiesChanged(network, linkProperties);
                    Log.i("connect", "onLinkPropertiesChanged: 当为此请求连接的框架所在的网络发生更改时调用LinkProperties");
                }

                @Override
                public void onLosing(Network network, int maxMsToLive) {
                    super.onLosing(network, maxMsToLive);
                    Log.i("connect", "onLosing: 网络正在断开");
                    Toast.makeText(context, "网络断开中", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
