package com.bw.movie.mvp.model.modelimpl;

import android.util.Log;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.SysMsgListBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SysMsgListModel  implements Contract.IMySysMsgModel
{
    ApiService apiService;

    @Override
    public void onISysMsgModel(HashMap<String, String> hashMap, int page, int count, final Contract.MySysMsgCallBack mySysMsgCallBack) {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.sysMsgList(hashMap,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SysMsgListBean>() {
                    @Override
                    public void accept(SysMsgListBean sysMsgListBean) throws Exception {
                        Log.e("myMessageBean",sysMsgListBean.toString());
                        mySysMsgCallBack.onSuccess(sysMsgListBean);
                    }
                });
    }
}
