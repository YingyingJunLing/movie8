package com.bw.movie.mvp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.md5.Md5;
import com.bw.movie.mvp.model.bean.BuyMovieBean;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.ScheduleListBean;
import com.bw.movie.mvp.model.utils.AlertAndAnimationUtils;
import com.bw.movie.mvp.presenter.presenterimpl.SeatPayPresenter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;
import com.qfdqc.views.seattable.SeatTable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseSeatActivity extends BaseActivity<Contract.ISeatPayView,SeatPayPresenter> implements Contract.ISeatPayView {

    public SeatTable seatTableView;
    @BindView(R.id.seatView)
    SeatTable seatView;
    @BindView(R.id.total_sum)
    TextView totalSum;
    @BindView(R.id.choose_true_img)
    ImageView chooseTrueImg;
    @BindView(R.id.choose_false_img)
    ImageView chooseFalseImg;
    @BindView(R.id.choose_seat_time_text)
    TextView chooseSeatTimeText;
    @BindView(R.id.choose_seat_time_hall)
    TextView chooseSeatTimeHall;
    private int scheduleId;
    private String beginTime;
    private String endTime;
    private String screeningHall;
    private double price;
    private int size;
    private String format;
    private SeatPayPresenter seatPayPresenter;
    private String userId;
    private String sessionId;
    private HashMap<String, String> hashMap;
    private String sign;
    private boolean b;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_seat);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {

        hashMap = new HashMap<>();
        hashMap.put("userId",userId);
        hashMap.put("sessionId",sessionId);

        final View view = View.inflate(this, R.layout.choose_seat_dialog, null);
        final AlertAndAnimationUtils alertAndAnimationUtils = new AlertAndAnimationUtils();
        final TextView pay_price = view.findViewById(R.id.pay_price);
        final RadioButton pay_type_1 = view.findViewById(R.id.pay_type_1);
        final RadioButton pay_type_2 = view.findViewById(R.id.pay_type_2);
        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTableView.setScreenName(screeningHall);//设置屏幕名称
        seatTableView.setMaxSelected(3);//设置最多选中

        chooseSeatTimeText.setText(beginTime+"-"+endTime);
        chooseSeatTimeHall.setText(screeningHall);

        pay_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay_type_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked==true){
                            b = true;
                        }
                    }
                });
                pay_type_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked==true){
                            b = true;
                        }
                    }
                });
                if (b == true){
                    Log.i("签名加密前",userId+scheduleId+size+"movie");
                    sign = Md5.MD5(userId+scheduleId+size+"movie");
                    Log.i("签名加密后",sign);
                    seatPayPresenter.onISeatPayPre(hashMap,scheduleId,size,sign);
                    Intent intent = new Intent(ChooseSeatActivity.this, MyRecordActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(ChooseSeatActivity.this,"请选择支付方式",Toast.LENGTH_LONG).show();
                }
            }
        });
        chooseTrueImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (size>0){
                    alertAndAnimationUtils.AlertDialog(ChooseSeatActivity.this,view);
                    pay_price.setText("需支付"+format+"元");
                }else {
                    Toast.makeText(ChooseSeatActivity.this,"请选定座位",Toast.LENGTH_SHORT).show();
                }
            }
        });
        chooseFalseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                ArrayList<String> selectedSeat = seatTableView.getSelectedSeat();
                size = selectedSeat.size();
                Log.i("选座", size + "");
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                format = decimalFormat.format(size * price);
                totalSum.setText(format);
            }

            @Override
            public void unCheck(int row, int column) {
                ArrayList<String> selectedSeat = seatTableView.getSelectedSeat();
                size = selectedSeat.size();
                Log.i("选座", size + "");
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String format = decimalFormat.format(size * price);
                totalSum.setText(format);
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(10, 15);
    }

    @Override
    protected SeatPayPresenter createPresenter() {
        seatPayPresenter = new SeatPayPresenter();
        return seatPayPresenter;
    }

    @Override
    protected void getData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getSchedule(ScheduleListBean.ResultBean resultBean) {
        scheduleId = resultBean.getId();
        beginTime = resultBean.getBeginTime();
        endTime = resultBean.getEndTime();
        screeningHall = resultBean.getScreeningHall();
        price = resultBean.getPrice();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getLogin(LoginBean.ResultBean resultBean){
        userId = resultBean.getUserId();
        sessionId = resultBean.getSessionId();
    }

    @Override
    public void onISeatPaySuccess(Object o) {
        if (o instanceof BuyMovieBean){
            BuyMovieBean buyMovieBean = (BuyMovieBean) o;
            Toast.makeText(this,buyMovieBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onISeatPayFail(String errorInfo) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
