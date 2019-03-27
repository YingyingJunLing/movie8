package com.bw.movie.mvp.model.modelimpl;

import android.util.Log;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.MyMessageBean;
import com.bw.movie.mvp.model.bean.RecordFeedBackBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyFeedBackModel implements Contract.IMyFeedBackModel
{

    private ApiService apiService;
    @Override
    public void IMyFeedBack(HashMap<String, String> hashMap, String comment, final Contract.MyFeedBackCallBack myFeedBackCallBack) {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.feedBack(hashMap,comment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RecordFeedBackBean>() {
                    @Override
                    public void accept(RecordFeedBackBean recordFeedBackBean) throws Exception {
                        myFeedBackCallBack.onSuccess(recordFeedBackBean);
                    }
                });
    }
}
