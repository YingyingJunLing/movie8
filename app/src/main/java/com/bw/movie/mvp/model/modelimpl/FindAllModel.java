package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.FindAllCinemasBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FindAllModel implements Contract.IFindAllModel {
    @Override
    public void onIFindAllModel(int page, int count, String cinemaName, final Contract.FindAllBack findAllBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getFindAll(page,count,cinemaName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FindAllCinemasBean>() {
                    @Override
                    public void accept(FindAllCinemasBean findAllCinemasBean) throws Exception {
                        findAllBack.onSuccess(findAllCinemasBean);
                    }
                });
    }
}
