package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.ScheduleListBean;
import com.bw.movie.mvp.view.activity.ChooseSeatActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ScheuleAdapter extends RecyclerView.Adapter<ScheuleAdapter.MyViewHolder> {
    private Context context;
    private List<ScheduleListBean.ResultBean> list;

    public ScheuleAdapter(Context context, List<ScheduleListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.scheule_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.schedule_item_type.setText(list.get(i).getScreeningHall());
        myViewHolder.schedule_item_time.setText(list.get(i).getBeginTime()+"--"+list.get(i).getEndTime()+"end");
        Log.i("时间",list.get(i).getBeginTime()+"--"+list.get(i).getEndTime()+"end");
        myViewHolder.schedule_item_price.setText(list.get(i).getPrice()+"¥");
        final ScheduleListBean.ResultBean resultBean = new ScheduleListBean.ResultBean(list.get(i).getId(),list.get(i).getBeginTime(), list.get(i).getEndTime(), list.get(i).getPrice(), list.get(i).getScreeningHall());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(resultBean);
                context.startActivity(new Intent(context,ChooseSeatActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView schedule_item_type;
        private final TextView schedule_item_time;
        private final TextView schedule_item_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            schedule_item_type = itemView.findViewById(R.id.schedule_item_type);
            schedule_item_time = itemView.findViewById(R.id.schedule_item_time);
            schedule_item_price = itemView.findViewById(R.id.schedule_item_price);
        }
    }
}
