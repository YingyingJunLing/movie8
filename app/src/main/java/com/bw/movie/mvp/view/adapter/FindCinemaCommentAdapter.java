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
import com.bw.movie.mvp.model.bean.FindCinemaCommentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;

public class FindCinemaCommentAdapter extends RecyclerView.Adapter<FindCinemaCommentAdapter.ViewHolder>
{
    Context context;
    FindCinemaCommentBean findCinemaCommentBean;
    public FindCinemaCommentAdapter(Context context, FindCinemaCommentBean findCinemaCommentBean) {
        this.context = context;
        this.findCinemaCommentBean =findCinemaCommentBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.cinemacomment_rev, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.camera_comment_content.setText(findCinemaCommentBean.getResult().get(i).getCommentContent());
        viewHolder.camera_comment_name.setText(findCinemaCommentBean.getResult().get(i).getCommentUserName());
        viewHolder.camera_comment_num.setText(findCinemaCommentBean.getResult().get(i).getGreatNum()+"");
      
        FrescoUtils.setPic(findCinemaCommentBean.getResult().get(i).getCommentHeadPic(),viewHolder.camera_comment_head);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(
                new java.util.Date(findCinemaCommentBean.getResult().get(i).getCommentTime()));
        viewHolder.camera_comment_time.setText(date);
        if (findCinemaCommentBean.getResult().get(i).getIsGreat()==1){
            Glide.with(context).load(R.mipmap.common_btn_prise_s).into(viewHolder.camera_comment_image_like);
        }else{
            Glide.with(context).load(R.mipmap.common_btn_prise_n).into(viewHolder.camera_comment_image_like);
        }
        //点赞按钮事件
        viewHolder .camera_comment_image_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClick!=null){
                    mOnClick.getdatas(findCinemaCommentBean.getResult().get(i).getCommentId(),findCinemaCommentBean.getResult().get(i).getIsGreat(),i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return findCinemaCommentBean.getResult().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView camera_comment_head;
        private final TextView camera_comment_name;
        private final TextView camera_comment_time;
        private final TextView camera_comment_content;
        private final TextView camera_comment_num;
        private final ImageView camera_comment_image_like;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            camera_comment_head = itemView.findViewById(R.id.camera_comment_head);
            camera_comment_name = itemView.findViewById(R.id.camera_comment_name);
            camera_comment_time = itemView.findViewById(R.id.camera_comment_time);
            camera_comment_content = itemView.findViewById(R.id.camera_comment_content);
            camera_comment_num = itemView.findViewById(R.id.camera_comment_num);
            camera_comment_image_like = itemView.findViewById(R.id.camera_comment_image_like);

        }
    }
    //点赞改变
    public void getlike(int position){
        findCinemaCommentBean.getResult().get(position).setIsGreat(1);
        findCinemaCommentBean.getResult().get(position).setGreatNum( findCinemaCommentBean.getResult().get(position).getGreatNum()+1);
        notifyDataSetChanged();
    }

    private OnClick mOnClick;
    public void setOnClick(OnClick mOnClick){
        this.mOnClick=mOnClick;
    }
    public interface OnClick{
        void getdatas(int id,int great,int position);
    }

}
