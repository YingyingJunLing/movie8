package com.bw.movie.mvp.view.frag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.presenter.presenterimpl.MyPresenter;
import com.bw.movie.mvp.view.activity.MyAttentionActivity;
import com.bw.movie.mvp.view.activity.MyFeedDBActivity;
import com.bw.movie.mvp.view.activity.MyMessageActivity;
import com.bw.movie.mvp.view.activity.MyRecordActivity;
import com.bw.movie.mvp.view.activity.MyVersionActivity;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Frag_My extends BaseFragment<Contract.IMyView,MyPresenter> implements Contract.IMyView, View.OnClickListener {

    private SharedPreferences sp;
    private String nickName;
    private String headPic;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        EventBus.getDefault().register(this);
        View view = View.inflate(getActivity(), R.layout.frag_my, null);
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getLoginData( LoginBean.ResultBean.UserInfoBean userInfoBean) {
         nickName = userInfoBean.getNickName();
         headPic = userInfoBean.getHeadPic();

    }

    @Override
    protected void initFragmentChildView(View view) {

        TextView myName = view.findViewById(R.id.my_name);
        ImageView myHead = view.findViewById(R.id.my_head);
        view.findViewById(R.id.my_message_image).setOnClickListener(this);
        view.findViewById(R.id.my_attention_image).setOnClickListener(this);
        view.findViewById(R.id.my_record_image).setOnClickListener(this);
        view.findViewById(R.id.my_feeddb_image).setOnClickListener(this);
        view.findViewById(R.id.my_version_image).setOnClickListener(this);

        myName.setText(nickName);
        Glide.with(getActivity())
                .load(headPic)
                .into(myHead);
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected MyPresenter createPresenter() {
        return null;
    }

    @Override
    public void onIMySuccess(Object o) {

    }

    @Override
    public void onIMyFail(String errorInfo) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.my_message_image:
                startActivity(new Intent(getActivity(),MyMessageActivity.class));
                break;
            case  R.id.my_attention_image:
                startActivity(new Intent(getActivity(),MyAttentionActivity.class));
                break;
            case  R.id.my_record_image:
                startActivity(new Intent(getActivity(),MyRecordActivity.class));
                break;
            case  R.id.my_feeddb_image:
                startActivity(new Intent(getActivity(),MyFeedDBActivity.class));
                break;
            case  R.id.my_version_image:
                startActivity(new Intent(getActivity(),MyVersionActivity.class));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
