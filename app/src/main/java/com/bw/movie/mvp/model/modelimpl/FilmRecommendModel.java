package com.bw.movie.mvp.model.modelimpl;

import com.bw.movie.mvp.model.api.ApiService;
import com.bw.movie.mvp.model.bean.RecommendCinemaBean;
import com.bw.movie.mvp.model.utils.RetrofitUtils;
import com.bw.movie.mvp.view.contract.Contract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FilmRecommendModel implements Contract.IRecommendModel {
    @Override
    public void onIRecommendModel(int page, int count, final Contract.RecommendBack recommendBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.getRecommend(page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RecommendCinemaBean>() {
                    @Override
                    public void accept(RecommendCinemaBean recommendCinemaBean) throws Exception {
                        recommendBack.onSuccess(recommendCinemaBean);
                    }
                });
    }
}
