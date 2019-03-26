package com.bw.movie.mvp.model.modelimpl;

import android.util.Log;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.MyMessageBean;
import com.bw.movie.mvp.model.bean.SysMsgListBean;
import com.bw.movie.mvp.model.bean.UpdateNameBean;
import com.bw.movie.mvp.model.bean.UpdatePassBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.activity.MainActivity;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyMessageModel implements Contract.IMyMessageModel {

    private ApiService apiService;

    @Override
    public void IMy(HashMap<String, String> hashMap, final Contract.MyMessageCallBack myMessageCallBack) {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.myMessage(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyMessageBean>() {
                    @Override
                    public void accept(MyMessageBean myMessageBean) throws Exception {
                        Log.e("myMessageBean",myMessageBean.toString());
                        myMessageCallBack.onSuccess(myMessageBean);
                    }
                });
    }

    @Override
    public void onIUpdatePass(HashMap<String, String> hashMap, HashMap<String, String> map, final Contract.MyMessageCallBack myMessageCallBack) {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.updateBean(hashMap,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdatePassBean>() {
                    @Override
                    public void accept(UpdatePassBean updatePassBean) throws Exception {
                        Log.e("myMessageBean",updatePassBean.toString());
                        myMessageCallBack.onSuccess(updatePassBean);
                    }
                });
    }

    @Override
    public void onIUpdateNmae(HashMap<String, String> hashMap, HashMap<String, String> map, final Contract.MyMessageCallBack myMessageCallBack) {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.updateNameBean(hashMap,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateNameBean>() {
                    @Override
                    public void accept(UpdateNameBean updateNameBean) throws Exception {
                        Log.e("myMessageBean",updateNameBean.toString());
                        myMessageCallBack.onSuccess(updateNameBean);
                    }
                });
    }


}
