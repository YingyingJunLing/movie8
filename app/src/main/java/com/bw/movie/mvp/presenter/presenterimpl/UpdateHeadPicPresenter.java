package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.UpdateHeadPicModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

import java.io.File;
import java.util.HashMap;

public class UpdateHeadPicPresenter extends BasePresenter<Contract.IUpdateHeadPicView> implements Contract.IUpdateHeadPicPre {

    private final UpdateHeadPicModel updateHeadPicModel;

    public UpdateHeadPicPresenter() {
        updateHeadPicModel = new UpdateHeadPicModel();
    }

    @Override
    public void onIUpdateHeadPicPre(HashMap<String, String> hashMap, File file)
    {
      updateHeadPicModel.IUpdateHeadPiBack(hashMap, file, new Contract.UpdateHeadPiclBack() {
          @Override
          public void onSuccess(Object o) {
              getView().onIUpdateHeadPicSuccess(o);
          }

          @Override
          public void onFail(String errorInfo) {

          }
      });

    }
}
