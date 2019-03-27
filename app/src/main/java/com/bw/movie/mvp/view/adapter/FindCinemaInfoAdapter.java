package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.FindCinemaInfoBean;

public class FindCinemaInfoAdapter extends RecyclerView.Adapter<FindCinemaInfoAdapter.ViewHolder> {
    Context context;
    FindCinemaInfoBean findCinemaInfoBean;
    public FindCinemaInfoAdapter(Context context, FindCinemaInfoBean findCinemaInfoBean)
    {
        this.context = context ;
        this.findCinemaInfoBean =findCinemaInfoBean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.findcinemainfo_rec, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.findcinemainfo_address.setText(findCinemaInfoBean.getResult().getAddress());

        viewHolder.findcinemainfo_phone_text.setText(findCinemaInfoBean.getResult().getPhone());


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView findcinemainfo_address;
        private final TextView findcinemainfo_self;
        private final TextView findcinemainfo_bus;
        private final TextView findcinemainfo_subway;
        private final TextView findcinemainfo_route_text;
        private final TextView findcinemainfo_phone_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findcinemainfo_address = itemView.findViewById(R.id.findcinemainfo_address);
            findcinemainfo_self = itemView.findViewById(R.id.findcinemainfo_self);
            findcinemainfo_bus = itemView.findViewById(R.id.findcinemainfo_bus);
            findcinemainfo_subway = itemView.findViewById(R.id.findcinemainfo_subway);
            findcinemainfo_route_text = itemView.findViewById(R.id.findcinemainfo_route_text);
            findcinemainfo_phone_text = itemView.findViewById(R.id.findcinemainfo_phone_text);


        }
    }
}
