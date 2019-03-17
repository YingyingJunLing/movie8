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
import com.bw.movie.mvp.model.bean.HotMovieBean;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.view.activity.MovieDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class BannerFlowAdapter extends RecyclerView.Adapter<BannerFlowAdapter.MyViewHolder> {
    private Context context;
    private List<HotMovieBean.ResultBean> list;
    private View view;

    public BannerFlowAdapter(Context context, List<HotMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = View.inflate(context, R.layout.banner_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        FrescoUtils.setPic(list.get(i).getImageUrl(),myViewHolder.cinema_img_simple);
        myViewHolder.cinema_nane_text.setText(list.get(i).getName());
        Log.i("名字",list.get(i).getName());
        final int id = list.get(i).getId();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("点击",id+"");
                MoviesDetailBean.ResultBean resultBean = new MoviesDetailBean.ResultBean(id);
                EventBus.getDefault().postSticky(resultBean);
                context.startActivity(new Intent(context,MovieDetailActivity.class));
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

        private final SimpleDraweeView cinema_img_simple;
        private final TextView cinema_nane_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cinema_img_simple = itemView.findViewById(R.id.cinema_img_simple);
            cinema_nane_text = itemView.findViewById(R.id.cinema_nane_text);
        }
    }
}
