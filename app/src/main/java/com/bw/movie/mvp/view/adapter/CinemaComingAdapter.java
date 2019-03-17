package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.ComingSoonMovieBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CinemaComingAdapter extends XRecyclerView.Adapter<CinemaComingAdapter.MyViewHolder> {
    private Context context;
    private List<ComingSoonMovieBean.ResultBean> list;

    public CinemaComingAdapter(Context context, List<ComingSoonMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.cinema_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        FrescoUtils.setPic(list.get(i).getImageUrl(),myViewHolder.cinema_item_simple);
        myViewHolder.cinema_item_text_name.setText(list.get(i).getName());
        myViewHolder.cinema_item_text_show.setText(list.get(i).getSummary());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView cinema_item_simple;
        private final TextView cinema_item_text_name;
        private final TextView cinema_item_text_show;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cinema_item_simple = itemView.findViewById(R.id.cinema_item_simple);
            cinema_item_text_name = itemView.findViewById(R.id.cinema_item_text_name);
            cinema_item_text_show = itemView.findViewById(R.id.cinema_item_text_show);
        }
    }
}