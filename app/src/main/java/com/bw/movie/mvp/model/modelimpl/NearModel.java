package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.FindNearCinemaBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NearModel implements Contract.INearModel {
    @Override
    public void onINearModel(int page, int count, final Contract.NearBack nearBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getFindNear(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FindNearCinemaBean>() {
                    @Override
                    public void accept(FindNearCinemaBean findNearCinemaBean) throws Exception {
                        nearBack.onSuccess(findNearCinemaBean);
                    }
                });
    }
}
