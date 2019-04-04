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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UpdateHeadPicModel implements Contract.IUpdateHeadPicModel {

    private ApiService apiService;


    @Override
    public void IUpdateHeadPiBack(HashMap<String, String> hashMap, File file, final Contract.UpdateHeadPiclBack updateHeadPiclBack) {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart=MultipartBody.Part.createFormData("image",file.getName(),requestFile);
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
