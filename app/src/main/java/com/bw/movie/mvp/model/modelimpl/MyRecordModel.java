package com.bw.movie.mvp.model.modelimpl;

import android.util.Log;

import com.bw.movie.mvp.model.api.Api;
import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.AttentionMovie;
import com.bw.movie.mvp.model.bean.MyRecordBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyRecordModel implements Contract.IMyRecotdModel
{
    private ApiService apiService;
    @Override
    public void IMyRecotd(HashMap<String, String> hashMap, int page, int count, String status, final Contract.MyRecotdCallBack myRecotdCallBack) {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.myRecord(hashMap,page,count,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyRecordBean>() {
                    @Override
                    public void accept(MyRecordBean myRecordBean) throws Exception {
                        Log.e("attentionFilm",myRecordBean.toString());
                        myRecotdCallBack.onSuccess(myRecordBean);
                    }
                });
    }
}
