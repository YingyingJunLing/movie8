package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.SysMsgListBean;

import java.text.SimpleDateFormat;

public class SysMsgListAdapter extends RecyclerView.Adapter<SysMsgListAdapter.ViewHolder> {
    Context context;
    SysMsgListBean sysMsgListBean;
    public SysMsgListAdapter(Context context, SysMsgListBean sysMsgListBean) {
        this.context = context ;
        this.sysMsgListBean = sysMsgListBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.sysmsg_recy_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        viewHolder.message_content.setText(sysMsgListBean.getResult().get(i).getContent());
        viewHolder.message_name.setText(sysMsgListBean.getResult().get(i).getTitle());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = simpleDateFormat.format(sysMsgListBean.getResult().get(i).getPushTime());
        viewHolder.message_time.setText(data);

    }

    @Override
    public int getItemCount() {
        return sysMsgListBean.getResult().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView message_content;
        private final TextView message_time;
        private final TextView message_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message_content = itemView.findViewById(R.id.message_content);
            message_time = itemView.findViewById(R.id.message_time);
            message_name = itemView.findViewById(R.id.message_name);
        }
    }
}
