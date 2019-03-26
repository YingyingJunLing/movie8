package com.bw.movie.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.ScheduleListBean;
import com.bw.movie.mvp.model.utils.AlertAndAnimationUtils;
import com.qfdqc.views.seattable.SeatTable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseSeatActivity extends AppCompatActivity {

    public SeatTable seatTableView;
    @BindView(R.id.seatView)
    SeatTable seatView;
    @BindView(R.id.total_sum)
    TextView totalSum;
    @BindView(R.id.choose_true_img)
    ImageView chooseTrueImg;
    @BindView(R.id.choose_false_img)
    ImageView chooseFalseImg;
    private String beginTime;
    private String endTime;
    private String screeningHall;
    private double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seat);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        final View view = View.inflate(this, R.layout.choose_seat_dialog, null);
        final AlertAndAnimationUtils alertAndAnimationUtils = new AlertAndAnimationUtils();

        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTableView.setScreenName("8号厅荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(3);//设置最多选中

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
                int size = selectedSeat.size();
                Log.i("选座", size + "");
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String format = decimalFormat.format(size * price);
                totalSum.setText(format);
            }

            @Override
            public void unCheck(int row, int column) {
                ArrayList<String> selectedSeat = seatTableView.getSelectedSeat();
                int size = selectedSeat.size();
                Log.i("选座", size + "");
                DecimalFormat decimalFormat = new DecimalFormat(".00");
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getSchedule(ScheduleListBean.ResultBean resultBean) {
        beginTime = resultBean.getBeginTime();
        endTime = resultBean.getEndTime();
        screeningHall = resultBean.getScreeningHall();
        price = resultBean.getPrice();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
