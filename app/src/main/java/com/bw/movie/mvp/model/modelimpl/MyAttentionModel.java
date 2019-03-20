package com.bw.movie.mvp.model.modelimpl;

import android.util.Log;

import com.bw.movie.mvp.model.api.Api;
import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.AttentionCamera;

import com.bw.movie.mvp.model.bean.AttentionMovie;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyAttentionModel implements Contract.IAttentionModel
{

    private ApiService apiService;

    @Override
    public void IAttentionMovie(HashMap<String, String> hashMap, int page, int count, final Contract.MyAttentionCallBack myAttentionCallBack) {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.attentionMovie(Api.MYATTENTIONMOCIE,hashMap,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AttentionMovie>() {
                    @Override
                    public void accept(AttentionMovie attentionMovie) throws Exception {
                        Log.e("attentionFilm",attentionMovie.toString());
                        myAttentionCallBack.onSuccess(attentionMovie);
                    }
                });
    }

    @Override
    public void IIAttentionCameras(HashMap<String, String> hashMap, int page, int count, final Contract.MyAttentionCallBack myAttentionCallBack) {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.attentionCamera(Api.MYATTENTIONCAMERA,hashMap,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AttentionCamera>() {
                    @Override
                    public void accept(AttentionCamera attentionCamera) throws Exception {
                        Log.e("attentionFilm",attentionCamera.toString());
                        myAttentionCallBack.onSuccess(attentionCamera);
                    }
                });
    }

}
