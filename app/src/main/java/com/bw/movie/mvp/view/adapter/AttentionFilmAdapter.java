package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;

import com.bw.movie.mvp.model.bean.AttentionMovie;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.view.activity.MovieDetailActivity;

import org.greenrobot.eventbus.EventBus;

public class AttentionFilmAdapter extends RecyclerView.Adapter<AttentionFilmAdapter.ViewHolder>
{
    Context context;
    AttentionMovie attentionFilm;
    public AttentionFilmAdapter(Context context, AttentionMovie attentionFilm)
    {
        this.context = context ;
        this .attentionFilm =attentionFilm;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.attention_film_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i)
    {
        viewHolder.name.setText(attentionFilm.getResult().get(i).getName());
        viewHolder.jie.setText(attentionFilm.getResult().get(i).getSummary());
        Glide.with(context)
                .load(attentionFilm.getResult().get(i).getImageUrl())
                .into(viewHolder.img);
        final int id = attentionFilm.getResult().get(i).getId();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoviesDetailBean.ResultBean attentionMovie = new MoviesDetailBean.ResultBean(id);
                EventBus.getDefault().postSticky(attentionMovie);
                context.startActivity(new Intent(context,MovieDetailActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return attentionFilm.getResult().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView jie;
        private final TextView name;
        ImageView img ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jie = itemView.findViewById(R.id.my_attention_movie_jie);
            name = itemView.findViewById(R.id.my_attention_movie_name);
           img = itemView.findViewById(R.id.my_attention_movie_img);
        }
    }
}
