package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.HotMovieBean;

import java.util.HashMap;
import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * @author zhangbo
 */
public class CinemaRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private HashMap hashMap;
    private final int T0=0;
    private final int T1=1;
    private final int T2=2;
    private final int T3=3;
    private int type;
    private RecyclerView hot_recycle;
    private RecyclerView release_recycle;
    private RecyclerView comingsoon_recycle;
    private RecyclerCoverFlow banner_flow;

    public CinemaRecycleAdapter(Context context, HashMap hashMap) {
        this.context = context;
        this.hashMap = hashMap;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (type==0){
            View v1 = View.inflate(context, R.layout.banner_cinema, null);
            return new MyViewHolder1(v1);
        }else if (type==1){
            View v2 = View.inflate(context, R.layout.hotmove_recycle, null);
            return new MyViewHolder2(v2);
        }else if (type==2){
            View v3 = View.inflate(context, R.layout.releasemovie_recycle, null);
            return new MyViewHolder3(v3);
        }else {
            View v4 = View.inflate(context, R.layout.comingsoonmovie_recycle, null);
            return new MyViewHolder4(v4);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (type==0){
            Object o = hashMap.get("0");
            if (o instanceof HotMovieBean){
                HotMovieBean hotMovieBean = (HotMovieBean) o;
                List<HotMovieBean.ResultBean> result = hotMovieBean.getResult();
                banner_flow.setAdapter(new BannerFlowAdapter(context,result));
            }
        }else if (type==1){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            hot_recycle.setLayoutManager(linearLayoutManager);
            CimeaItemAdapter cimeaItemAdapter = new CimeaItemAdapter(context,hashMap,type);
            hot_recycle.setAdapter(cimeaItemAdapter);
        }else if (type==2){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            release_recycle.setLayoutManager(linearLayoutManager);
            CimeaItemAdapter cimeaItemAdapter = new CimeaItemAdapter(context,hashMap,type);
            release_recycle.setAdapter(cimeaItemAdapter);
        }else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            comingsoon_recycle.setLayoutManager(linearLayoutManager);
            CimeaItemAdapter cimeaItemAdapter = new CimeaItemAdapter(context,hashMap,type);
            comingsoon_recycle.setAdapter(cimeaItemAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return hashMap.size();
    }

    @Override
    public int getItemViewType(int position) {
        type=position%hashMap.size();
        switch (type){
            case 0:
                return T0;
            case 1:
                return T1;
            case 2:
                return T2;
            case 3:
                return T3;
        }
        return type;
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            banner_flow = itemView.findViewById(R.id.banner_flow);
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            hot_recycle = itemView.findViewById(R.id.hot_recycle);
        }
    }

    class MyViewHolder3 extends RecyclerView.ViewHolder {
        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);
            release_recycle = itemView.findViewById(R.id.release_recycle);
        }
    }

    class MyViewHolder4 extends RecyclerView.ViewHolder {
        public MyViewHolder4(@NonNull View itemView) {
            super(itemView);
            comingsoon_recycle = itemView.findViewById(R.id.comingsoon_recycle);
        }
    }
}
