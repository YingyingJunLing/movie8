package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.FilmRecommendModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

public class FilmRecommendPresenter extends BasePresenter<Contract.IRecommendView> implements Contract.IRecommendPre {

    private FilmRecommendModel filmRecommendModel;

    public FilmRecommendPresenter() {
        filmRecommendModel = new FilmRecommendModel();
    }

    @Override
    public void onIRecommendPre(int page, int count) {
        filmRecommendModel.onIRecommendModel(page, count, new Contract.RecommendBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onIRecommendSuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }
}
