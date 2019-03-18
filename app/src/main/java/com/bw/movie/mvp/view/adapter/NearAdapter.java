package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.FindNearCinemaBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class NearAdapter extends XRecyclerView.Adapter<NearAdapter.MyViewHolder> {
    private Context context;
    private List<FindNearCinemaBean.ResultBean> list;

    public NearAdapter(Context context, List<FindNearCinemaBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.film_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        FrescoUtils.setPic(list.get(i).getLogo(),myViewHolder.film_simple);
        myViewHolder.file_name.setText(list.get(i).getName());
        myViewHolder.file_address.setText(list.get(i).getAddress());
        myViewHolder.file_long.setText(list.get(i).getFollowCinema()+".0km");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView film_simple;
        private final TextView file_name;
        private final TextView file_address;
        private final TextView file_long;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            film_simple = itemView.findViewById(R.id.film_simple);
            file_name = itemView.findViewById(R.id.file_name);
            file_address = itemView.findViewById(R.id.file_address);
            file_long = itemView.findViewById(R.id.file_long);
        }
    }
}
