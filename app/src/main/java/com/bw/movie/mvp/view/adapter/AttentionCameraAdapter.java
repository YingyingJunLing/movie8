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
import com.bw.movie.mvp.model.bean.AttentionCamera;


public class AttentionCameraAdapter extends RecyclerView.Adapter<AttentionCameraAdapter.ViewHolder>
{
    Context context;
    AttentionCamera attentionCamera;
    public AttentionCameraAdapter(Context context, AttentionCamera attentionCamera)
    {
        this.context =context ;
        this.attentionCamera= attentionCamera;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.my_attention_camera_rec_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
       viewHolder.name.setText(attentionCamera.getResult().get(i).getName());
       viewHolder.address.setText(attentionCamera.getResult().get(i).getAddress());
        Glide.with(context)
                .load(attentionCamera.getResult().get(i).getLogo())
                .into(viewHolder.img);

    }

    @Override
    public int getItemCount() {
        return attentionCamera.getResult().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView address;
        private final TextView name;
        private final ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.my_attention_camera_address);
            name = itemView.findViewById(R.id.my_attention_camera_name);
            img = itemView.findViewById(R.id.my_attention_camera_img);

        }
    }
}
