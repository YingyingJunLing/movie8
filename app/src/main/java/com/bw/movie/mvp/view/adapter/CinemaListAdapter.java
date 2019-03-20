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
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.CinemaListBean;
import com.bw.movie.mvp.view.activity.ScheduleListActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CinemaListAdapter extends RecyclerView.Adapter<CinemaListAdapter.MyViewHolder> {
    private Context context;
    private List<CinemaListBean.ResultBean> list;
    private View view;

    public CinemaListAdapter(Context context, List<CinemaListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = View.inflate(context, R.layout.film_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        FrescoUtils.setPic(list.get(i).getLogo(),myViewHolder.film_simple);
        myViewHolder.file_name.setText(list.get(i).getName());
        Log.i("根据电影ID", list.get(i).getName());
        myViewHolder.file_address.setText(list.get(i).getAddress());
        myViewHolder.file_long.setText(list.get(i).getFollowCinema()+".0km");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("影院ID", list.get(i).getId()+"");
                int cid = list.get(i).getId();
                CinemaListBean.ResultBean resultBean = new CinemaListBean.ResultBean(cid);
                Log.i("影院ID", resultBean.getId()+"");
                EventBus.getDefault().postSticky(resultBean);
                context.startActivity(new Intent(context,ScheduleListActivity.class));
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
        private final SimpleDraweeView film_simple;
        private final TextView file_name;
        private final TextView file_address;
        private final TextView file_long;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            film_simple = itemView.findViewById(R.id.film_simple);
            file_name = itemView.findViewById(R.id.file_name);
            file_address = itemView.findViewById(R.id.file_address);
            file_long = itemView.findViewById(R.id.file_long);
        }
    }
}
