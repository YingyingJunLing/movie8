package com.bw.movie.presenter;


import com.bw.movie.base.BasePresenter;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.model.utils.RetrofitUtils;

/**
 * @Auther: 赵维鸣
 * @Date: 2019/3/2 10:38:15
 * @Description:
 */
public class MainPresenter extends BasePresenter {

    private final RetrofitUtils retrofitUtils;
    private final ApiService apiService;

    public MainPresenter(){
        retrofitUtils = RetrofitUtils.getInstance();
        apiService = retrofitUtils.create(ApiService.class);


    }
}
