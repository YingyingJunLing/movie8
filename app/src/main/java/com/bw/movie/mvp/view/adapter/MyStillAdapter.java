package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.view.activity.MovieDetailActivity;

public class MyStillAdapter extends RecyclerView.Adapter<MyStillAdapter.ViewHolder> {
    Context context;
    MoviesDetailBean.ResultBean result;
    public MyStillAdapter(Context context, MoviesDetailBean.ResultBean result)
    {
        this.context =context ;
        this.result = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.mystill_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        Glide.with(context)
                .load(result.getPosterList().get(i))
                .into(viewHolder.img);

    }

    @Override
    public int getItemCount() {
        return result.getPosterList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.still_img);
        }
    }
}
