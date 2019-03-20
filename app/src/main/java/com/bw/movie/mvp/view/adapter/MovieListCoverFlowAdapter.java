package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.MovieListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @author zhangbo
 * 影院电影详情
 */
public class MovieListCoverFlowAdapter extends RecyclerView.Adapter<MovieListCoverFlowAdapter.MyViewHolder> {
    private Context context;
    private List<MovieListBean.ResultBean> list;
    private View view;

    public MovieListCoverFlowAdapter(Context context, List<MovieListBean.ResultBean> list) {
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
