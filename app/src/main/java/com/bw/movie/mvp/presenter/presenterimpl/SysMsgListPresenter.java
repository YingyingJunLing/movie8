package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.SysMsgListModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

public class SysMsgListPresenter extends BasePresenter<Contract.IMySysMsgView> implements Contract.IMySysMsgPre
{

    private final SysMsgListModel sysMsgListModel;

    public SysMsgListPresenter() {
        sysMsgListModel = new SysMsgListModel();
    }

    @Override
    public void onISysMsgPre(HashMap<String, String> hashMap, int page, int count) {
        sysMsgListModel.onISysMsgModel(hashMap,page,count, new Contract.MySysMsgCallBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onISysMsg(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }
}
