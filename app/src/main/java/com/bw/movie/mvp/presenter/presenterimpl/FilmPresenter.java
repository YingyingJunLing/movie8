package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

public class FilmPresenter extends BasePresenter<Contract.IFilmView> implements Contract.IFilmPre {
    @Override
    public void onIFilmPre(String url) {

    }
}
