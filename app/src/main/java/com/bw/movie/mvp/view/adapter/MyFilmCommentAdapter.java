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
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.MovieCommentBean;
import com.bw.movie.mvp.view.activity.MovieDetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;

public class MyFilmCommentAdapter extends RecyclerView.Adapter<MyFilmCommentAdapter.ViewHolder>
{
    Context context;
    MovieCommentBean movieCommentBeanResult;
    public MyFilmCommentAdapter(Context context, MovieCommentBean movieCommentBeanResult) {
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
    public void onBindViewHolder(@NonNull final MyFilmCommentAdapter.ViewHolder viewHolder, final int i)
    {
        viewHolder.film_comment_content.setText(movieCommentBeanResult.getResult().get(i).getCommentContent());
        viewHolder.film_comment_name.setText(movieCommentBeanResult.getResult().get(i).getCommentUserName());
        viewHolder.film_comment_num.setText(movieCommentBeanResult.getResult().get(i).getGreatNum()+"");
        viewHolder.film_comment_num_num.setText(movieCommentBeanResult.getResult().get(i).getReplyNum()+"");
        FrescoUtils.setPic(movieCommentBeanResult.getResult().get(i).getCommentHeadPic(),viewHolder.film_comment_head);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(
                new java.util.Date(movieCommentBeanResult.getResult().get(i).getCommentTime()));
        viewHolder.film_comment_time.setText(date);
        if (movieCommentBeanResult.getResult().get(i).getIsGreat().equals("1")){

            Glide.with(context).load(R.mipmap.common_btn_prise_s).into(viewHolder.film_comment_image_like);
        }else{

            Glide.with(context).load(R.mipmap.common_btn_prise_n).into(viewHolder.film_comment_image_like);
        }
        //点赞按钮事件
       viewHolder .film_comment_image_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClick!=null){
                    mOnClick.getdata(movieCommentBeanResult.getResult().get(i).getCommentId(),movieCommentBeanResult.getResult().get(i).getIsGreat(),i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieCommentBeanResult.getResult().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView film_comment_head;
        private final TextView film_comment_name;
        private final TextView film_comment_time;
        private final TextView film_comment_content;
        private final TextView film_comment_num,film_comment_num_num;
        private final ImageView film_comment_image_like;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            film_comment_head = itemView.findViewById(R.id.film_comment_head);
            film_comment_name = itemView.findViewById(R.id.film_comment_name);
            film_comment_time = itemView.findViewById(R.id.film_comment_time);
            film_comment_content = itemView.findViewById(R.id.film_comment_content);
            film_comment_num = itemView.findViewById(R.id.film_comment_num);
            film_comment_image_like = itemView.findViewById(R.id.film_comment_image_like);

            film_comment_num_num =   itemView.findViewById(R.id.film_comment_num_num);


        }
    }
    //点赞改变
    public void getlike(int position){
        movieCommentBeanResult.getResult().get(position).setIsGreat("1");
        movieCommentBeanResult.getResult().get(position).setGreatNum( movieCommentBeanResult.getResult().get(position).getGreatNum()+1);
        notifyDataSetChanged();
    }
    //取消点赞改变
    public void getcancel(int position){
        movieCommentBeanResult.getResult().get(position).setIsGreat("2");
        movieCommentBeanResult.getResult().get(position).setGreatNum( movieCommentBeanResult.getResult().get(position).getGreatNum()-1);
        notifyDataSetChanged();
    }
    private OnClick mOnClick;
    public void setOnClick(OnClick mOnClick){
        this.mOnClick=mOnClick;
    }


    public interface OnClick{
        void getdata(int id,String great,int position);
    }

}
