package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.MyRecordBean;
import com.bw.movie.mvp.view.activity.MyRecordActivity;

import java.text.SimpleDateFormat;

public class MyRecordAdapter extends RecyclerView.Adapter<MyRecordAdapter.ViewHolder> {
    Context context;
    MyRecordBean myRecordBean;
    public MyRecordAdapter(Context context, MyRecordBean myRecordBean) {
        this.context =context ;
        this.myRecordBean =myRecordBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.my_record_recy_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        viewHolder.my_record_camera.setText("影院 ："+myRecordBean.getResult().get(i).getCinemaName());
        viewHolder.my_record_money.setText("金额 ：  "+myRecordBean.getResult().get(i).getPrice());
        viewHolder.my_record_name.setText(myRecordBean.getResult().get(i).getMovieName());
        viewHolder.my_record_num.setText("数量 ："+myRecordBean.getResult().get(i).getAmount());
        viewHolder.my_record_yingting.setText("影厅 ："+myRecordBean.getResult().get(i).getCinemaName());
        viewHolder.my_record_hao.setText("订单号 ："+myRecordBean.getResult().get(i).getOrderId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        //String data = simpleDateFormat.format(myRecordBean.getResult().get(i).get);
        String createTime = simpleDateFormat.format(myRecordBean.getResult().get(i).getCreateTime());
        viewHolder.my_record_time.setText(createTime);
        //viewHolder.my_record_seetime.setText(data);
        int status = myRecordBean.getResult().get(i).getStatus();
        if(status==1)
        {
            viewHolder.my_record_status.setText("待付款");
        }else
        {
            viewHolder.my_record_status.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return myRecordBean.getResult().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView my_record_time;
        private final TextView my_record_name;
        private final TextView my_record_status;
        private final TextView my_record_camera;
        private final TextView my_record_yingting;
        private final TextView my_record_seetime;
        private final TextView my_record_num;
        private final TextView my_record_money;
        private final TextView my_record_hao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            my_record_hao = itemView.findViewById(R.id.my_record_hao);
            my_record_time = itemView.findViewById(R.id.my_record_time);
            my_record_name = itemView.findViewById(R.id.my_record_name);
            my_record_status = itemView.findViewById(R.id.my_record_status);
            my_record_camera = itemView.findViewById(R.id.my_record_camera);
            my_record_yingting = itemView.findViewById(R.id.my_record_yingting);
            my_record_seetime = itemView.findViewById(R.id.my_record_seetime);
            my_record_num = itemView.findViewById(R.id.my_record_num);
            my_record_money = itemView.findViewById(R.id.my_record_money);
        }
    }
}
