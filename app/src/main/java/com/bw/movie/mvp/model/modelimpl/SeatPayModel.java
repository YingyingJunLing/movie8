package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.BuyMovieBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SeatPayModel implements Contract.ISeatPayModel {
    @Override
    public void onISeatPayModel(HashMap<String, String> hashMap, int scheduleId, int amount, String sign, final Contract.SeatPayBack seatPayBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getBuyMovie(hashMap,scheduleId,amount,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<BuyMovieBean>() {
                    @Override
                    public void accept(BuyMovieBean buyMovieBean) throws Exception {
                        seatPayBack.onSuccess(buyMovieBean);
                    }
                });
    }
}
