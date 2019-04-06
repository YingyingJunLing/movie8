package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.MovieCommentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyMovieCommentAdapter extends RecyclerView.Adapter<MyMovieCommentAdapter.ViewHolder>
{
    Context context;
    List<MovieCommentBean.ResultBean> list;


    public MyMovieCommentAdapter(Context context) {
        this.context = context ;

    }

    public void setMovieComment(List<MovieCommentBean.ResultBean> list) {
        if (list!=null||list.size()!=0){
            this.list = list;
            list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public MyMovieCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.film_comment, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyMovieCommentAdapter.ViewHolder viewHolder, final int i)
    {
        viewHolder.film_comment_content.setText(list.get(i).getCommentContent());
        viewHolder.film_comment_name.setText(list.get(i).getCommentUserName());
        viewHolder.film_comment_num.setText(list.get(i).getGreatNum()+"");
       viewHolder.film_comment__replay_num.setText(list.get(i).getReplyNum()+"");
        FrescoUtils.setPic(list.get(i).getCommentHeadPic(),viewHolder.film_comment_head);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(
                new java.util.Date(list.get(i).getCommentTime()));
        viewHolder.film_comment_time.setText(date);
        int isGreat = list.get(i).getIsGreat();
        Log.e("isGreat",isGreat+"");
        if (list.get(i).getIsGreat()==1){
            viewHolder.film_comment_image_like.setImageResource(R.mipmap.common_btn_prise_s);
        }else{
            viewHolder.film_comment_image_like.setImageResource(R.mipmap.common_btn_prise_n);
        }
        //点赞按钮事件
        viewHolder .film_comment_image_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClick!=null){
                    mOnClick.getdata(list.get(i).getCommentId(),list.get(i).getIsGreat(),i);
                }
            }
        });
        //回复评论点击事件
        viewHolder.film_comment__replay_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnClicks !=null)
                {
                    mOnClicks.getdatas(i,list.get(i).getCommentId(),list.get(i).getReplyNum());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView film_comment_head;
        private final TextView film_comment_name;
        private final TextView film_comment_time;
        private final TextView film_comment_content;
        private final TextView film_comment_num,film_comment__replay_num;
        private final ImageView film_comment_image_like,film_comment__replay_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            film_comment_head = itemView.findViewById(R.id.film_comment_head);
            film_comment_name = itemView.findViewById(R.id.film_comment_name);
            film_comment_time = itemView.findViewById(R.id.film_comment_time);
            film_comment_content = itemView.findViewById(R.id.film_comment_content);
            film_comment_num = itemView.findViewById(R.id.film_comment_num);
            film_comment_image_like = itemView.findViewById(R.id.film_comment_image_like);
            film_comment__replay_num= itemView.findViewById(R.id.film_comment__replay_num);
            film_comment__replay_img =itemView.findViewById(R.id.film_comment__replay_img);
        }
    }

    //点赞改变
    public void getlike(int position){
        list.get(position).setIsGreat(1);
        list.get(position).setGreatNum( list.get(position).getGreatNum()+1);
        notifyDataSetChanged();
    }
    private OnClick mOnClick;
    public void setOnClick(OnClick mOnClick){
        this.mOnClick=mOnClick;
    }
    public interface OnClick{
        void getdata(int id,int great,int position);
    }
    private OnClicks mOnClicks;
    public void setOnClicks(OnClicks mOnClicks){
        this.mOnClicks=mOnClicks;
    }
    public interface OnClicks{
        void getdatas(int position,int commentId,int num);
    }

}
