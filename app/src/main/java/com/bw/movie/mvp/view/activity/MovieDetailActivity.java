package com.bw.movie.mvp.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.CancelFollowMovieBean;
import com.bw.movie.mvp.model.bean.FollowMovieBean;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.MovieCommentBean;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.model.utils.AlertAndAnimationUtils;
import com.bw.movie.mvp.presenter.presenterimpl.MovieDetailPresenter;
import com.bw.movie.mvp.view.adapter.MyFilmCommentAdapter;
import com.bw.movie.mvp.view.adapter.MyForecastAdapter;
import com.bw.movie.mvp.view.adapter.MyStillAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zhangbo
 * 详情页面
 */
public class MovieDetailActivity extends BaseActivity<Contract.IMovieDetailView, MovieDetailPresenter> implements Contract.IMovieDetailView {

    @BindView(R.id.movie_detail_text)
    TextView movieDetailText;
    @BindView(R.id.movie_detail_img)
    SimpleDraweeView movieDetailImg;
    @BindView(R.id.movie_datail_btn)
    Button movieDatailBtn;
    @BindView(R.id.movie_prevue_btn)
    Button moviePrevueBtn;
    @BindView(R.id.movie_picture_btn)
    Button moviePictureBtn;
    @BindView(R.id.movie_commit_btn)
    Button movieCommitBtn;
    @BindView(R.id.collection_sel)
    ImageView collectionSel;
    @BindView(R.id.movie_betail_fan)
    Button movieBetailFan;
    @BindView(R.id.buy_btn)
    Button buyBtn;
    private MovieDetailPresenter movieDetailPresenter;
    private int id;
    private MoviesDetailBean moviesDetailBean;
    private MoviesDetailBean.ResultBean result;
    private AlertAndAnimationUtils alertAndAnimationUtils;
    private MovieCommentBean movieCommentBean;
    private RecyclerView rec3;
    public int page = 1;
    public int count = 10;
    private View view4;
    private MyFilmCommentAdapter myFilmCommentAdapter;
    private ImageView collection_sel;
    private String userId;
    private String sessionId;
    private HashMap<String, String> hashMap;
    private int followMovie;


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEvent(MoviesDetailBean.ResultBean resultBean) {
        id = resultBean.getId();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getLoginData(LoginBean.ResultBean resultBean) {
        userId = resultBean.getUserId();
        sessionId = resultBean.getSessionId();

    }


    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        alertAndAnimationUtils = new AlertAndAnimationUtils();
        hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        hashMap.put("sessionId", sessionId);
        Log.e("hashMapsss", hashMap.toString());

    }


    @Override
    protected void initView() {
        Button fan = findViewById(R.id.movie_betail_fan);
        //复选框  关注 不关注
        collection_sel = findViewById(R.id.collection_sel);
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collection_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (followMovie == 1) {
                    Glide.with(MovieDetailActivity.this)
                            .load(R.drawable.com_icon_collection_selected)
                            .into(collection_sel);
                    movieDetailPresenter.onIFollowMovie(id, hashMap);
                    followMovie = 2;
                } else {
                    Glide.with(MovieDetailActivity.this)
                            .load(R.drawable.com_icon_collection_default)
                            .into(collection_sel);
                    movieDetailPresenter.onICancelFollowMovie(id, hashMap);
                    followMovie = 1;
                }
            }
        });

    }

    @Override
    protected MovieDetailPresenter createPresenter() {
        movieDetailPresenter = new MovieDetailPresenter();
        return movieDetailPresenter;
    }

    @Override
    protected void getData() {
        movieDetailPresenter.onIMovieDetailPre(id);
        movieDetailPresenter.onIMovieCommenPre(id, page, count);
    }

    @Override
    public void onIMovieDetailSuccess(Object o) {
        Log.i("详情", o.toString());
        /**
         * 详情
         */
        if (o instanceof MoviesDetailBean) {
            moviesDetailBean = (MoviesDetailBean) o;
            result = moviesDetailBean.getResult();
            Log.i("详情名字", result.getName());
            movieDetailText.setText(result.getName());
            followMovie = moviesDetailBean.getResult().getFollowMovie();
            Log.e("followMovie", followMovie + "");
            FrescoUtils.setPic(result.getImageUrl(), movieDetailImg);
            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MovieDetailActivity.this, CinemaListActivity.class);
                    startActivity(intent);
                }
            });
        }
        /**
         * 影视的评价
         */
        if (o instanceof MovieCommentBean) {
            movieCommentBean = (MovieCommentBean) o;
            Log.e("movieCommentBean", movieCommentBean.toString());
            if (movieCommentBean != null) {

                myFilmCommentAdapter = new MyFilmCommentAdapter(MovieDetailActivity.this, movieCommentBean);
            }
        }
        /**
         * 关注电影
         */
        if (o instanceof FollowMovieBean) {
            FollowMovieBean followMovieBean = (FollowMovieBean) o;
            if (followMovieBean != null) {
                Toast.makeText(MovieDetailActivity.this, followMovieBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        /**
         * 取消关注
         */
        if (o instanceof CancelFollowMovieBean) {
            CancelFollowMovieBean cancelFollowMovieBean = (CancelFollowMovieBean) o;
            if (cancelFollowMovieBean != null) {
                Toast.makeText(MovieDetailActivity.this, cancelFollowMovieBean.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onIMovieDetailFail(String errorInfo) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.movie_datail_btn, R.id.movie_prevue_btn, R.id.movie_picture_btn, R.id.movie_commit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //详情
            case R.id.movie_datail_btn:
                //电影详情
                final View view1 = View.inflate(MovieDetailActivity.this, R.layout.details_dialog_item, null);
                SimpleDraweeView img = view1.findViewById(R.id.details_dialog_img);
                TextView type = view1.findViewById(R.id.film_details_type);
                TextView director = view1.findViewById(R.id.film_details_director);
                TextView time = view1.findViewById(R.id.film_details_time);
                TextView context = view1.findViewById(R.id.synopsis_text_context);
                TextView fields = view1.findViewById(R.id.film_details_fields);
                ImageButton dis_dialog = view1.findViewById(R.id.dialog_dismiss_ibt);
                img.setImageURI(result.getImageUrl());
                type.setText("类型:" + " " + result.getMovieTypes());
                director.setText("导演:" + " " + result.getDirector());
                time.setText("时长:" + " " + result.getDuration());
                fields.setText("产地:" + " " + result.getPlaceOrigin());
                context.setText(result.getSummary());
                //隐藏dialog
                dis_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertAndAnimationUtils.hideDialog();
                    }
                });
                alertAndAnimationUtils.AlertDialog(MovieDetailActivity.this, view1);

                break;
            //预告
            case R.id.movie_prevue_btn:
                final View view2 = View.inflate(MovieDetailActivity.this, R.layout.forecast_dialog_item, null);
                RecyclerView rec = view2.findViewById(R.id.forecast_dialog_rec);
                ImageButton dis_dialog1 = view2.findViewById(R.id.dialog_dismiss_ibt);
                rec.setLayoutManager(new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                rec.setAdapter(new MyForecastAdapter(MovieDetailActivity.this, result));
                //隐藏dialog
                dis_dialog1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertAndAnimationUtils.hideDialog();
                    }
                });
                alertAndAnimationUtils.AlertDialog(MovieDetailActivity.this, view2);

                break;
            //剧照
            case R.id.movie_picture_btn:
                final View view3 = View.inflate(MovieDetailActivity.this, R.layout.still_dialog_item, null);
                RecyclerView rec1 = view3.findViewById(R.id.still_dialog_rec);
                rec1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                rec1.setAdapter(new MyStillAdapter(MovieDetailActivity.this, result));
                ImageButton dis_dialog2 = view3.findViewById(R.id.dialog_dismiss_ibt);
                //隐藏dialog
                dis_dialog2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertAndAnimationUtils.hideDialog();
                    }
                });
                alertAndAnimationUtils.AlertDialog(MovieDetailActivity.this, view3);

                break;
            //影评
            case R.id.movie_commit_btn:
                view4 = View.inflate(MovieDetailActivity.this, R.layout.comment_dialog_item, null);
                rec3 = view4.findViewById(R.id.comment_dialog_rec);
                rec3.setLayoutManager(new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                rec3.setAdapter(myFilmCommentAdapter);
                ImageButton dis_dialog3 = view4.findViewById(R.id.dialog_dismiss_ibt);
                //隐藏dialog
                dis_dialog3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertAndAnimationUtils.hideDialog();
                    }
                });
                alertAndAnimationUtils.AlertDialog(MovieDetailActivity.this, view4);

                break;
        }
    }
}