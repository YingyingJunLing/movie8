package com.bw.movie.mvp.presenter.presenterimpl;

import com.bw.movie.mvp.model.modelimpl.SeatPayModel;
import com.bw.movie.mvp.presenter.base.BasePresenter;
import com.bw.movie.mvp.view.contract.Contract;

import java.util.HashMap;

public class SeatPayPresenter extends BasePresenter<Contract.ISeatPayView> implements Contract.ISeatPayPre {

    private SeatPayModel seatPayModel;

    public SeatPayPresenter() {
        seatPayModel = new SeatPayModel();
    }

    @Override
    public void onISeatPayPre(HashMap<String, String> hashMap, int scheduleId, int amount, String sign) {
        seatPayModel.onISeatPayModel(hashMap,scheduleId,amount,sign, new Contract.SeatPayBack() {
            @Override
            public void onSuccess(Object o) {
                getView().onISeatPaySuccess(o);
            }

            @Override
            public void onFail(String errorInfo) {

            }
        });
    }
}
