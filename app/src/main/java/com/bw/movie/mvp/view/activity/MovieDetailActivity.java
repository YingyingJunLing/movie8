package com.bw.movie.mvp.view.activity;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.AddMovieCommentBean;
import com.bw.movie.mvp.model.bean.CancelFollowMovieBean;
import com.bw.movie.mvp.model.bean.CommentReplyBean;
import com.bw.movie.mvp.model.bean.FollowMovieBean;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.MovieCommentBean;
import com.bw.movie.mvp.model.bean.MovieCommentGreate;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.model.utils.AlertAndAnimationUtils;
import com.bw.movie.mvp.model.utils.ClickUtils;
import com.bw.movie.mvp.model.utils.NetworkErrorUtils;
import com.bw.movie.mvp.presenter.presenterimpl.MovieDetailPresenter;
import com.bw.movie.mvp.view.adapter.MyMovieCommentAdapter;
import com.bw.movie.mvp.view.adapter.MyForecastAdapter;
import com.bw.movie.mvp.view.adapter.MyStillAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.HashMap;
import java.util.List;
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
    private MyMovieCommentAdapter myMovieCommentAdapter;
    private ImageView collection_sel;
    private String userId;
    private String sessionId;
    private HashMap<String, String> hashMap;
    private int followMovie;
    private MovieCommentGreate movieCommentGreate;
    private ImageView comment_publish;
    private HashMap<String, String> map;
    private TextView movie_comment_send;
    private List<MovieCommentBean.ResultBean> list;
    private RelativeLayout comment_movie;
    private InputMethodManager imm;
    public static MovieDetailActivity movieDetailActivity;

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
        movieDetailActivity=this;
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
                if(followMovie ==1)
                {
                    movieDetailPresenter.onIFollowMovie(id, hashMap);
                    followMovie =2;
                }else{
                    movieDetailPresenter.onICancelFollowMovie(id, hashMap);
                    followMovie =1;
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
            if(moviesDetailBean.getResult().getFollowMovie() == 1)
            {
                Glide.with(MovieDetailActivity.this)
                        .load(R.drawable.com_icon_collection_selected)
                        .into(collection_sel);
            }else{
                Glide.with(MovieDetailActivity.this)
                        .load(R.drawable.com_icon_collection_default)
                        .into(collection_sel);
            }
            FrescoUtils.setPic(result.getImageUrl(), movieDetailImg);
            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MobclickAgent.onEvent(MovieDetailActivity.this, "buyBtn");
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
            list = movieCommentBean.getResult();
            if (movieCommentBean != null)
                myMovieCommentAdapter = new MyMovieCommentAdapter(MovieDetailActivity.this,list);
               rec3.setAdapter(myMovieCommentAdapter);
            myMovieCommentAdapter.setOnClick(new MyMovieCommentAdapter.OnClick() {
                @Override
                public void getdata(int id, int great, int position) {
                    if (great == 1){
                        //已点赞，需取消
                        movieDetailPresenter.onIMovieCommentGreatePre(hashMap, id);
                    }else{
                        //未点赞需点赞
                        movieDetailPresenter.onIMovieCommentGreatePre(hashMap, id);
                        myMovieCommentAdapter.getlike(position);
                    }
                }
            });
            //点击吊起系统键盘
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
               myMovieCommentAdapter.setOnClicks(new MyMovieCommentAdapter.OnClicks() {
                   @Override
                   public void getdatas(final int i, final int commentId,  int nums) {
                       comment_movie.setVisibility(View.VISIBLE);

                       final EditText  movie_comment_content = view4.findViewById(R.id.movie_comment_content);
                       imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                       //发送点击事件
                       movie_comment_send.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               String s = movie_comment_content.getText().toString();
                               map = new HashMap<>();
                               map.put("commentId", String.valueOf(commentId));
                               map.put("replyContent",s);
                               movieDetailPresenter.onICommentReplayPre(hashMap,map);
                               comment_movie.setVisibility(View.GONE);
                               imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                               int replyNum = movieCommentBean.getResult().get(i).getReplyNum();
                               replyNum++;
                               movieCommentBean.getResult().get(i).setReplyNum(replyNum);
                               myMovieCommentAdapter.notifyDataSetChanged();
                           }
                       });
                   }
               });
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
        //评论点赞
        if(o instanceof MovieCommentGreate)
        {
            movieCommentGreate = (MovieCommentGreate) o;
            if(movieCommentGreate !=null)
            {
                Toast.makeText(MovieDetailActivity.this, movieCommentGreate.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

        //添加影片的评论
        if(o instanceof AddMovieCommentBean)
        {
            AddMovieCommentBean addMovieCommentBean = (AddMovieCommentBean) o;
            if(addMovieCommentBean !=null){
                Toast.makeText(MovieDetailActivity.this,addMovieCommentBean.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }
        /**
         * 添加用户对评论的回复
         */
        if(o instanceof CommentReplyBean)
        {
            CommentReplyBean commentReplyBean = (CommentReplyBean) o;
            if(commentReplyBean != null)
            {
                Toast.makeText(MovieDetailActivity.this,commentReplyBean.getMessage(),Toast.LENGTH_SHORT).show();
                myMovieCommentAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onIMovieDetailFail(String errorInfo) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieDetailPresenter.onDestroy();
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
                if(ClickUtils.isFastClick())
                {
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
                }
                break;
            //预告
            case R.id.movie_prevue_btn:
                if(ClickUtils.isFastClick())
                {
                    final View view2 = View.inflate(MovieDetailActivity.this, R.layout.forecast_dialog_item, null);
                    RecyclerView rec = view2.findViewById(R.id.forecast_dialog_rec);
                    ImageButton dis_dialog1 = view2.findViewById(R.id.dialog_dismiss_ibt);
                    rec.setLayoutManager(new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                    rec.setAdapter(new MyForecastAdapter(MovieDetailActivity.this, result));
                    //隐藏dialog
                    dis_dialog1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((AudioManager) getSystemService(
                                    Context.AUDIO_SERVICE)).requestAudioFocus(
                                    new AudioManager.OnAudioFocusChangeListener() {
                                        @Override
                                        public void onAudioFocusChange(int focusChange) {

                                        }
                                    }, AudioManager.STREAM_MUSIC,
                                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                            alertAndAnimationUtils.hideDialog();
                        }
                    });
                    alertAndAnimationUtils.AlertDialog(MovieDetailActivity.this, view2);

                }
                break;
            //剧照
            case R.id.movie_picture_btn:
                if(ClickUtils.isFastClick())
                {
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
                }
                break;
            //影评
            case R.id.movie_commit_btn:
                if(ClickUtils.isFastClick())
                {
                    view4 = View.inflate(MovieDetailActivity.this, R.layout.comment_dialog_item, null);
                    rec3 = view4.findViewById(R.id.comment_dialog_rec);
                    rec3.setLayoutManager(new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                    movieDetailPresenter.onIMovieCommenPre(id, page, count);
                    comment_movie = view4.findViewById(R.id.comment_movie);
                    movie_comment_send= view4.findViewById(R.id.movie_comment_send);
                    comment_publish= view4.findViewById(R.id.comment_publish);
                    //添加评论
                    comment_publish.setOnClickListener(new View.OnClickListener() {
                        private EditText movie_comment_content;
                        @Override
                        public void onClick(View v) {
                            comment_movie.setVisibility(View.VISIBLE);
                            movie_comment_content = view4.findViewById(R.id.movie_comment_content);
                            //发送点击事件
                            movie_comment_send.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String s = movie_comment_content.getText().toString();
                                    Log.e("lallallal",s);
                                    map = new HashMap<>();
                                    map.put("movieId", String.valueOf(id));
                                    map.put("commentContent",s);
                                    Log.e("mapsss", map +"");
                                    movieDetailPresenter.onIAddmovieCommentPre(hashMap,map);
                                    comment_movie.setVisibility(View.GONE);
                                    myMovieCommentAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    });
                    ImageButton dis_dialog3 = view4.findViewById(R.id.dialog_dismiss_ibt);
                    //隐藏dialog
                    dis_dialog3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertAndAnimationUtils.hideDialog();
                        }
                    });
                    alertAndAnimationUtils.AlertDialog(MovieDetailActivity.this, view4);
                }
                break;
        }
    }
}