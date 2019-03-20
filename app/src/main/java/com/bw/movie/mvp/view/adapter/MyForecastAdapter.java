package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.view.activity.MovieDetailActivity;

public class MyForecastAdapter extends RecyclerView.Adapter<MyForecastAdapter.ViewHolder> {
    Context context;
    MoviesDetailBean.ResultBean result;
    public MyForecastAdapter(Context context, MoviesDetailBean.ResultBean result)
    {
        this.context = context ;
        this.result = result;
    }

    @NonNull
    @Override
    public MyForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.myforecast_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyForecastAdapter.ViewHolder viewHolder, int i)
    {
        viewHolder.videoView.setVideoPath(result.getShortFilmList().get(i).getVideoUrl());

    }

    @Override
    public int getItemCount() {
        return result.getShortFilmList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final VideoView videoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video);
        }
    }
}
