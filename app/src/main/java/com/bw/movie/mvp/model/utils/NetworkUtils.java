package com.bw.movie.mvp.model.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class NetworkUtils {
    public void NetworkDynamicJudgment(final Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.requestNetwork(new NetworkRequest.Builder().build(), new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    Log.i("connect", "onAvailable: 获取到网络连接");
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

    //判断是否有网络连接
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
