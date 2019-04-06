package com.bw.movie.mvp.view.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.fresco.FrescoUtils;
import com.bw.movie.mvp.model.bean.AddCimeraCommentBean;

import com.bw.movie.mvp.model.bean.CinemaCommentGreatBean;
import com.bw.movie.mvp.model.bean.CinemaIfoBean;
import com.bw.movie.mvp.model.bean.CinemaListBean;
import com.bw.movie.mvp.model.bean.FindCinemaCommentBean;
import com.bw.movie.mvp.model.bean.FindCinemaInfoBean;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.MovieListBean;
import com.bw.movie.mvp.model.bean.ScheduleListBean;
import com.bw.movie.mvp.model.utils.AlertAndAnimationUtils;
import com.bw.movie.mvp.model.utils.NetworkErrorUtils;
import com.bw.movie.mvp.presenter.presenterimpl.MovieListPresenter;
import com.bw.movie.mvp.view.adapter.FindCinemaCommentAdapter;
import com.bw.movie.mvp.view.adapter.FindCinemaInfoAdapter;
import com.bw.movie.mvp.view.adapter.MovieListCoverFlowAdapter;

import com.bw.movie.mvp.view.adapter.ScheuleAdapter;
import com.bw.movie.mvp.view.base.BaseActivity;
import com.bw.movie.mvp.view.contract.Contract;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

public class MovieListActivity extends BaseActivity<Contract.IMovieListView, MovieListPresenter> implements Contract.IMovieListView {

    @BindView(R.id.movie_list_logo)
    SimpleDraweeView movieListLogo;
    @BindView(R.id.movie_list_name)
    TextView movieListName;
    @BindView(R.id.movie_list_address)
    TextView movieListAddress;
    @BindView(R.id.movie_list_cover_flow)
    RecyclerCoverFlow movieListCoverFlow;
    @BindView(R.id.movie_list_recycle)
    RecyclerView movieListRecycle;
    private MovieListPresenter movieListPresenter;
    private int cid;
    private int mid;
    private NetworkErrorUtils networkErrorUtils;
    private String userId;
    private String sessionId;
    private HashMap<String, String> hashMap;
    private AlertAndAnimationUtils alertAndAnimationUtils;
    private TextView ping;
    private TextView xiang;
    private RecyclerView rec2;
    private RecyclerView rec;
    private FindCinemaCommentAdapter findCinemaCommentAdapter;
    private CinemaCommentGreatBean cinemaCommentGreatBean;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void getCinema(CinemaListBean.ResultBean resultBean) {
        cid = resultBean.getId();
    }

    //得到userId    sessionId
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getLoginData(LoginBean.ResultBean resultBean) {
        userId = resultBean.getUserId();
        sessionId = resultBean.getSessionId();

    }

    @Override
    protected void initView() {
        networkErrorUtils = new NetworkErrorUtils(MovieListActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        movieListRecycle.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected MovieListPresenter createPresenter() {
        movieListPresenter = new MovieListPresenter();
        return movieListPresenter;
    }

    @Override
    protected void getData() {
        hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        hashMap.put("sessionId", sessionId);
        alertAndAnimationUtils = new AlertAndAnimationUtils();
        movieListPresenter.onIMovieListCinemaPre(cid);
        //影院照片点击，弹出详情，和评论
        movieListLogo.setOnClickListener(new View.OnClickListener() {

            private TextView movie_comment_send;
            private RelativeLayout comment_movie;

            @Override
            public void onClick(View v) {

                final View view2 = View.inflate(MovieListActivity.this, R.layout.recommend_dialog_item, null);

                rec = view2.findViewById(R.id.recomm_dialog_rec_ping);

                rec2 = view2.findViewById(R.id.recommend_dialog_xiang);
                rec.setLayoutManager(new LinearLayoutManager(MovieListActivity.this, LinearLayoutManager.VERTICAL, false));
                rec2.setLayoutManager(new LinearLayoutManager(MovieListActivity.this, LinearLayoutManager.VERTICAL, false));
                rec.setVisibility(View.VISIBLE);
                rec2.setVisibility(View.GONE);
                //默认展示详情
                movieListPresenter.onIFindCimeraInfoPre(hashMap, cid);
                ImageButton dis_dialog1 = view2.findViewById(R.id.dialog_dismiss_ibt);
                //详情
                xiang = view2.findViewById(R.id.recommend_xiang);
                //评价
                ping = view2.findViewById(R.id.recommend_ping);
                //点击评论发送按钮
                movie_comment_send = view2.findViewById(R.id.movie_comment_send);
                //隐藏的评论布局
                comment_movie = view2.findViewById(R.id.comment_movie);
                //详情点击事件
                xiang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieListPresenter.onIFindCimeraInfoPre(hashMap, cid);
                        rec.setVisibility(View.VISIBLE);
                        rec2.setVisibility(View.GONE);

                    }
                });
                //评价点击事件
                ping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView comment_publish = view2.findViewById(R.id.comment_publish);
                        rec.setVisibility(View.GONE);
                        comment_publish.setVisibility(View.VISIBLE);
                        int page = 1;
                        int count = 10;
                        movieListPresenter.onICimemaCommentPre(hashMap, cid, page, count);
                        rec2.setVisibility(View.VISIBLE);
                        //添加评论点击事件
                        comment_publish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final EditText movie_comment_content = view2.findViewById(R.id.movie_comment_content);
                                comment_movie.setVisibility(View.VISIBLE);
                                //发送点击事件
                                movie_comment_send.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String s = movie_comment_content.getText().toString();
                                        HashMap<String, String> map = new HashMap<>();
                                        map.put("cinemaId", String.valueOf(cid));
                                        map.put("commentContent", s);
                                        movieListPresenter.onIAddCinemaCommentPre(hashMap, map);
                                        comment_movie.setVisibility(View.VISIBLE);
                                    }
                                });

                            }
                        });
                    }
                });

                //隐藏dialog
                dis_dialog1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertAndAnimationUtils.hideDialog();
                    }
                });
                alertAndAnimationUtils.AlertDialog(MovieListActivity.this, view2);

            }
        });
    }

    @Override
    public void onIMovieListSuccess(Object o) {
        if (o instanceof MovieListBean) {
            MovieListBean movieListBean = (MovieListBean) o;
            if (movieListBean.getStatus().equals("0000")) {
                final List<MovieListBean.ResultBean> result = movieListBean.getResult();
                MovieListCoverFlowAdapter movieListCoverFlowAdapter = new MovieListCoverFlowAdapter(this, result);
                movieListCoverFlow.setAdapter(movieListCoverFlowAdapter);
                mid = result.get(0).getId();
                movieListPresenter.onIMovieListCinemaMoviePre(cid, mid);
                movieListCoverFlow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
                    @Override
                    public void onItemSelected(int position) {
                        mid = result.get(position).getId();
                        movieListPresenter.onIMovieListCinemaMoviePre(cid, mid);
                    }
                });
            }
        }
    }

    @Override
    public void onIFindCimeraInfoSuccess(Object o) {
        if (o instanceof FindCinemaInfoBean) {
            FindCinemaInfoBean findCinemaInfoBean = (FindCinemaInfoBean) o;
            FindCinemaInfoAdapter findCinemaInfoAdapter = new FindCinemaInfoAdapter(MovieListActivity.this, findCinemaInfoBean);
            rec.setAdapter(findCinemaInfoAdapter);
        }

    }

    @Override
    public void onIMovieListCinemaSuccess(Object o) {
        if (o instanceof CinemaIfoBean) {
            CinemaIfoBean cinemaIfoBean = (CinemaIfoBean) o;
            CinemaIfoBean.ResultBean result = cinemaIfoBean.getResult();
            FrescoUtils.setPic(result.getLogo(), movieListLogo);
            movieListName.setText(result.getName());
            movieListAddress.setText(result.getAddress());
        }
        movieListPresenter.onIMovieListPre(cid);
    }

    @Override
    public void onIMovieListCinemaMovieSuccess(Object o) {
        Log.i("相关档期", o.toString());
        if (o instanceof ScheduleListBean) {
            ScheduleListBean scheduleListBean = (ScheduleListBean) o;
            if (scheduleListBean.getResult().size() > 0) {
                List<ScheduleListBean.ResultBean> result = scheduleListBean.getResult();
                movieListRecycle.setAdapter(new ScheuleAdapter(this, result));
            }
        }
    }

    @Override
    public void onIMovieListFail(String errorInfo) {

    }

    @Override
    public void onICimemaCommentSuccess(Object o) {
        if (o instanceof FindCinemaCommentBean) {
            FindCinemaCommentBean findCinemaCommentBean = (FindCinemaCommentBean) o;

            if (findCinemaCommentBean.getResult().size() == 0) {
                Toast.makeText(MovieListActivity.this, findCinemaCommentBean.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                findCinemaCommentAdapter = new FindCinemaCommentAdapter(MovieListActivity.this, findCinemaCommentBean);
                rec2.setAdapter(findCinemaCommentAdapter);
                findCinemaCommentAdapter.setOnClick(new FindCinemaCommentAdapter.OnClick() {
                    @Override
                    public void getdatas(int id, int great, int position) {
                        if (great == 1) {
                            //已点赞，需取消
                            movieListPresenter.onICimemaCommentGreatePre(hashMap, id);
                        } else {
                            movieListPresenter.onICimemaCommentGreatePre(hashMap, id);
                            findCinemaCommentAdapter.getlike(position);
                        }
                    }
                });
            }
        }

    }

    @Override
    public void onICimemaCommentGreateSuccess(Object o) {
        if (o instanceof CinemaCommentGreatBean) {
            cinemaCommentGreatBean = (CinemaCommentGreatBean) o;
            if (cinemaCommentGreatBean != null) {
                Toast.makeText(MovieListActivity.this, cinemaCommentGreatBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onIAddCinemaComment(Object o) {
        //添加影片的评论
        if (o instanceof AddCimeraCommentBean) {
            AddCimeraCommentBean addCimeraCommentBean = (AddCimeraCommentBean) o;
            if (addCimeraCommentBean != null) {
                Toast.makeText(MovieListActivity.this, addCimeraCommentBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieListPresenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
