package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.Api;
import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.UpdateHeadPicBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import java.io.File;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class UpdateHeadPicModel implements Contract.IUpdateHeadPicModel {

    private ApiService apiService;


    @Override
    public void IUpdateHeadPiBack(HashMap<String, String> hashMap, File file, final Contract.UpdateHeadPiclBack updateHeadPiclBack) {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        MultipartBody.Part filePart=MultipartBody.Part.createFormData("image",file.getName());
        apiService.updateHeadPic(hashMap,filePart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateHeadPicBean>() {
                    @Override
                    public void accept(UpdateHeadPicBean updateHeadPicBean) throws Exception {
                        updateHeadPiclBack.onSuccess(updateHeadPicBean);
                    }
                });
    }
}
