package com.bw.movie.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.ComingSoonMovieBean;
import com.bw.movie.mvp.model.bean.HotMovieBean;
import com.bw.movie.mvp.model.bean.ReleaseMovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhangbo
 */
public class CimeaItemAdapter extends RecyclerView.Adapter<CimeaItemAdapter.MyViewHolder> {
    private Context context;
    private HashMap hashMap;
    private int type;
    private List<HotMovieBean.ResultBean> listhot;
    private List<ReleaseMovieBean.ResultBean> listrelease;
    private List<ComingSoonMovieBean.ResultBean> listcoming;

    public CimeaItemAdapter(Context context, HashMap hashMap, int type) {
        this.context = context;
        this.hashMap = hashMap;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_cinema, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (type==1){
            Object o1 = hashMap.get("1");
            if (o1 instanceof HotMovieBean) {
                HotMovieBean hotMovieBean = (HotMovieBean) o1;
                listhot = hotMovieBean.getResult();
            }
            myViewHolder.cinema_nane_text.setText(listhot.get(i).getName());
            FrescoUtils.setPic(listhot.get(i).getImageUrl(),myViewHolder.cinema_img_simple);
        }else if (type==2){
            Object o2 = hashMap.get("2");
            if (o2 instanceof ReleaseMovieBean){
                ReleaseMovieBean releaseMovieBean = (ReleaseMovieBean) o2;
                listrelease = releaseMovieBean.getResult();
            }
            myViewHolder.cinema_nane_text.setText(listrelease.get(i).getName());
            FrescoUtils.setPic(listrelease.get(i).getImageUrl(),myViewHolder.cinema_img_simple);
        }else {
            Object o3 = hashMap.get("3");
            if (o3 instanceof ReleaseMovieBean){
                ReleaseMovieBean releaseMovieBean = (ReleaseMovieBean) o3;
                listrelease = releaseMovieBean.getResult();
            }
            myViewHolder.cinema_nane_text.setText(listcoming.get(i).getName());
            FrescoUtils.setPic(listcoming.get(i).getImageUrl(),myViewHolder.cinema_img_simple);
        }
    }

    @Override
    public int getItemCount() {
        if (type==1){
            Object o1 = hashMap.get("1");
            if (o1 instanceof HotMovieBean) {
                HotMovieBean hotMovieBean = (HotMovieBean) o1;
                listhot = hotMovieBean.getResult();
            }
            return listhot.size();
        }else if (type==2){
            Object o2 = hashMap.get("2");
            if (o2 instanceof ReleaseMovieBean){
                ReleaseMovieBean releaseMovieBean = (ReleaseMovieBean) o2;
                listrelease = releaseMovieBean.getResult();
            }
            return listrelease.size();
        }else {
            Object o3 = hashMap.get("3");
            if (o3 instanceof ComingSoonMovieBean){
                ComingSoonMovieBean comingSoonMovieBean = (ComingSoonMovieBean) o3;
                listcoming = comingSoonMovieBean.getResult();
            }
            return listcoming.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView cinema_nane_text;
        private final SimpleDraweeView cinema_img_simple;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            if (type==1) {
                Object o1 = hashMap.get("1");
                if (o1 instanceof HotMovieBean) {
                    HotMovieBean hotMovieBean = (HotMovieBean) o1;
                    listhot = hotMovieBean.getResult();
                }
            }else if (type==2){
                Object o2 = hashMap.get("2");
                if (o2 instanceof ReleaseMovieBean){
                    ReleaseMovieBean releaseMovieBean = (ReleaseMovieBean) o2;
                    listrelease = releaseMovieBean.getResult();
                }
            }else {
                Object o3 = hashMap.get("3");
                if (o3 instanceof ComingSoonMovieBean){
                    ComingSoonMovieBean comingSoonMovieBean = (ComingSoonMovieBean) o3;
                    listcoming = comingSoonMovieBean.getResult();
                }
            }
            cinema_img_simple = itemView.findViewById(R.id.cinema_img_simple);
            cinema_nane_text = itemView.findViewById(R.id.cinema_nane_text);
        }
    }
}
