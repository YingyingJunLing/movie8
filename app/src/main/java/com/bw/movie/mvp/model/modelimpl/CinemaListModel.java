package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.CinemaListBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CinemaListModel implements Contract.ICinemaListModel {
    @Override
    public void onICinemaListModel(int movieID, final Contract.CinemaListBack cinemaListBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getCinemaList(movieID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CinemaListBean>() {
                    @Override
                    public void accept(CinemaListBean cinemaListBean) throws Exception {
                        cinemaListBack.onSuccess(cinemaListBean);
                    }
                });
    }
}
