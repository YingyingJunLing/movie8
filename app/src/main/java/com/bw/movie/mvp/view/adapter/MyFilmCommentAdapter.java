package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.MovieCommentBean;
import com.bw.movie.mvp.view.activity.MovieDetailActivity;

import java.util.List;

public class MyFilmCommentAdapter extends RecyclerView.Adapter<MyFilmCommentAdapter.ViewHolder>
{
    Context context;
    List<MovieCommentBean.ResultBean> movieCommentBeanResult;
    public MyFilmCommentAdapter(Context context, List<MovieCommentBean.ResultBean> movieCommentBeanResult) {
        this.context = context ;
        this.movieCommentBeanResult = movieCommentBeanResult;
    }

    @NonNull
    @Override
    public MyFilmCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.film_comment, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFilmCommentAdapter.ViewHolder viewHolder, int i)
    {
        viewHolder.film_comment_content.setText(movieCommentBeanResult.get(i).getHotComment());
        viewHolder.film_comment_name.setText(movieCommentBeanResult.get(i).getCommentUserName());
        viewHolder.film_comment_num.setText(movieCommentBeanResult.get(i).getGreatNum());
        Glide.with(context)
                .load(movieCommentBeanResult.get(i).getCommentHeadPic())
                .into(viewHolder.film_comment_head);

    }

    @Override
    public int getItemCount() {
        return movieCommentBeanResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView film_comment_head;
        private final TextView film_comment_name;
        private final TextView film_comment_time;
        private final TextView film_comment_content;
        private final TextView film_comment_num;
        private final ImageView film_comment_image_like;
        private final ImageView film_comment_image_disike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            film_comment_head = itemView.findViewById(R.id.film_comment_head);
            film_comment_name = itemView.findViewById(R.id.film_comment_name);
            film_comment_time = itemView.findViewById(R.id.film_comment_time);
            film_comment_content = itemView.findViewById(R.id.film_comment_content);
            film_comment_num = itemView.findViewById(R.id.film_comment_num);
            film_comment_image_like = itemView.findViewById(R.id.film_comment_image_like);
            film_comment_image_disike = itemView.findViewById(R.id.film_comment_image_disike);


        }
    }
}
