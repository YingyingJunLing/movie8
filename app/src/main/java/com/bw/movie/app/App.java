package com.bw.movie.app;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.mvp.view.base.BaseActivity;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
<<<<<<< HEAD
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
=======
>>>>>>> ccffb9c5d608469e1182bfb34fc6e22dab6a4dff
import com.umeng.commonsdk.UMConfigure;

import java.io.File;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        File sd = Environment.getExternalStorageDirectory();
        String mPath = sd.getPath() + "/image";
        File file = new File(mPath);
        if (!file.exists()){
            file.mkdir();
        }
        Fresco.initialize(App.this,ImagePipelineConfig.newBuilder(App.this)
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(this)
                                .setBaseDirectoryPath(file)
                                .build()
                )
                .build()
        );
        /**
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
         * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "1fe6a20054bcef865eeb0991ee84525b");

    }
<<<<<<< HEAD
=======


>>>>>>> ccffb9c5d608469e1182bfb34fc6e22dab6a4dff
}
