package com.bw.movie.mvp.model.api;

import com.bw.movie.mvp.model.bean.AddCimeraCommentBean;
import com.bw.movie.mvp.model.bean.AddMovieCommentBean;
import com.bw.movie.mvp.model.bean.AttentionCamera;
import com.bw.movie.mvp.model.bean.AttentionMovie;
import com.bw.movie.mvp.model.bean.BuyMovieBean;
import com.bw.movie.mvp.model.bean.CancelFollowMovieBean;
import com.bw.movie.mvp.model.bean.CinemaCommentGreatBean;
import com.bw.movie.mvp.model.bean.CinemaIfoBean;
import com.bw.movie.mvp.model.bean.CinemaListBean;
import com.bw.movie.mvp.model.bean.ComingSoonMovieBean;
import com.bw.movie.mvp.model.bean.CommentReplyBean;
import com.bw.movie.mvp.model.bean.FindAllCinemasBean;
import com.bw.movie.mvp.model.bean.FindCinemaCommentBean;
import com.bw.movie.mvp.model.bean.FindCinemaInfoBean;
import com.bw.movie.mvp.model.bean.FindNearCinemaBean;
import com.bw.movie.mvp.model.bean.FollowMovieBean;
import com.bw.movie.mvp.model.bean.HotMovieBean;
import com.bw.movie.mvp.model.bean.LoginBean;
import com.bw.movie.mvp.model.bean.MovieCommentBean;
import com.bw.movie.mvp.model.bean.MovieCommentGreate;
import com.bw.movie.mvp.model.bean.MovieListBean;
import com.bw.movie.mvp.model.bean.MoviesDetailBean;
import com.bw.movie.mvp.model.bean.MyMessageBean;
import com.bw.movie.mvp.model.bean.MyRecordBean;
import com.bw.movie.mvp.model.bean.PayBean;
import com.bw.movie.mvp.model.bean.PayStatusBean;
import com.bw.movie.mvp.model.bean.RecommendCinemaBean;
import com.bw.movie.mvp.model.bean.RecommendMovieBean;
import com.bw.movie.mvp.model.bean.RecordFeedBackBean;
import com.bw.movie.mvp.model.bean.RegBean;
import com.bw.movie.mvp.model.bean.ScheduleListBean;
import com.bw.movie.mvp.model.bean.SysMsgListBean;
import com.bw.movie.mvp.model.bean.UpdateHeadPicBean;
import com.bw.movie.mvp.model.bean.UpdateNameBean;
import com.bw.movie.mvp.model.bean.UpdatePassBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    //登录
    @POST
    @FormUrlEncoded
    Observable<LoginBean> login(@Url String url, @FieldMap HashMap<String, String> hashMap);
   //注册
    @POST
    @FormUrlEncoded
    Observable<RegBean> reg(@Url String url, @FieldMap HashMap<String, String> hashMap);
    //我的信息
    @GET(Api.MyMessage)
    Observable<MyMessageBean> myMessage (@HeaderMap HashMap<String ,String> hashMap);
    //修改密码
    @POST(Api.UPDATEPASS)
    @FormUrlEncoded
    Observable<UpdatePassBean> updateBean(@HeaderMap HashMap<String ,String> hashMap,@FieldMap HashMap<String, String> map);
    //热门电影类表
    @GET(Api.HOTMOVIE)
    Observable<HotMovieBean> getHotMovie(@Query("page")int page,@Query("count")int count);

    //上映电影类表
    @GET(Api.RELEASEMOVIE)
    Observable<RecommendMovieBean> getReleaseMovie(@Query("page")int page, @Query("count")int count);

    //即将上映电影类表
    @GET(Api.COMINGSOONMOVIE)
    Observable<ComingSoonMovieBean> getComingSoonMovie(@Query("page")int page, @Query("count")int count);

    //根据电影ID查询电影详情
    @GET(Api.MOVIEDETAIL)
    Observable<MoviesDetailBean> getMoviesDetail(@Query("movieId")int movieId);

    //推荐影院
    @GET(Api.RECOMMENDCINEMA)
    Observable<RecommendCinemaBean> getRecommend(@Query("page")int page, @Query("count")int count);

    //附近影院
    @GET(Api.FINDNEARCINEMA)
    Observable<FindNearCinemaBean> getFindNear(@Query("page")int page, @Query("count")int count);
    //影院评论
    @GET(Api.MOVIECOMMENT)
    Observable<MovieCommentBean> movieComment(@Query("movieId")int movieId, @Query("page")int page, @Query("count")int count);

    //根据电影ID查询当前排片该电影的影院列表
    @GET(Api.CINEMALIST)
    Observable<CinemaListBean> getCinemaList(@Query("movieId")int movieId);

    //根据电影ID和影院ID查询电影排期列表
    @GET(Api.SCHEDILELIST)
    Observable<ScheduleListBean> getScheduleList(@Query("cinemasId")int cinemasId,@Query("movieId")int movieId);

    //影院详情
    @GET(Api.CINEMAINFO)
    Observable<CinemaIfoBean> getCinemaInfo(@Query("cinemaId")int cinemaId);


    //电影的关注
    @GET(Api.FOLLOWMOVIE)
    Observable<FollowMovieBean> followMovie(@Query("cinemaId")int cinemaId,@HeaderMap HashMap<String ,String> hashMap);
   //取消关注
    @GET(Api.CANCELFOLLOWMOVIE)
    Observable<CancelFollowMovieBean> cancelFollowMovie(@Query("cinemaId")int cinemaId, @HeaderMap HashMap<String ,String> hashMap);
    //关注影片
    @GET
    Observable<AttentionMovie> attentionMovie(@Url String url, @HeaderMap HashMap<String ,String> hashMap, @Query("page")int page, @Query("count")int count);
    //关注影院
    @GET
    Observable<AttentionCamera> attentionCamera(@Url String url, @HeaderMap HashMap<String ,String> hashMap, @Query("page")int page, @Query("count")int count);
    //根据影院ID查询该影院当前排期的电影列表
    @GET(Api.MOVIELIST)
    Observable<MovieListBean> getMovieList(@Query("cinemaId")int cinemaId);
    //.用户购票记录查询列表
    @GET(Api.MYRECORD)
    Observable<MyRecordBean> myRecord(@HeaderMap HashMap<String ,String> hashMap, @Query("page")int page, @Query("count")int count,@Query("status")String status);

    //电影评论点赞
    @POST(Api.MOVIECOMMENTGREATE)
    @FormUrlEncoded
    Observable<MovieCommentGreate> movieCommentGreate(@HeaderMap HashMap<String ,String> hashMap, @Field("commentId") int commentId);
    //意见反馈
    @POST(Api.RECORDFEEDBACk)
    Observable<RecordFeedBackBean> feedBack(@HeaderMap HashMap<String ,String> hashMap, @Query("content")String content);
    //查询影院的详情
    @GET(Api.FINDCINEMAINFO)
    Observable<FindCinemaInfoBean> findCinemaInfo(@HeaderMap HashMap<String ,String> hashMap, @Query("cinemaId")int cinemaId);
    //查看影院的评价列表
    @GET(Api.FINDCINEMACOMMENT)
    Observable<FindCinemaCommentBean> cinemaComment(@HeaderMap HashMap<String ,String> hashMap, @Query("cinemaId")int cinemaId,@Query("page")int page, @Query("count")int count);
    //影院评论点赞
    @POST(Api.CINEMACOMMENTGREATE)
    @FormUrlEncoded
    Observable<CinemaCommentGreatBean> cinemaCommentGreate(@HeaderMap HashMap<String ,String> hashMap, @Field("commentId") int commentId);
    //购票下单
    @POST(Api.BUYMOVIE)
    @FormUrlEncoded
    Observable<BuyMovieBean> getBuyMovie(@HeaderMap HashMap<String ,String> hashMap ,@Field("scheduleId") int scheduleId,@Field("amount") int amount,@Field("sign") String sign);

    //微信登录
    @POST(Api.WECHATLOGIN)
    @FormUrlEncoded
    Observable<LoginBean> getWeChatLogin(@Field("code") String code);
    //微信支付
    @POST(Api.PAY)
    @FormUrlEncoded
    Observable<PayBean> getPay(@HeaderMap HashMap<String ,String> hashMap, @Field("payType") int payType,@Field("orderId") String orderId);
    //支付宝支付
    @POST(Api.PAY)
    @FormUrlEncoded
    Observable<PayStatusBean> getPayStatus(@HeaderMap HashMap<String ,String> hashMap, @Field("payType") int payType, @Field("orderId") String orderId);
    //模糊查询
    @GET(Api.FINDALLCINEMAS)
    Observable<FindAllCinemasBean> getFindAll(@Query("page")int page, @Query("count")int count,@Query("cinemaName")String cinemaName);
 //修改用户信息
 @POST(Api.UPDATEMESSAGE)
 @FormUrlEncoded
 Observable<UpdateNameBean> updateNameBean(@HeaderMap HashMap<String ,String> hashMap, @FieldMap HashMap<String, String> map);
 //查询系统列表
 @GET(Api.SYSMSGLIST)
 Observable<SysMsgListBean> sysMsgList(@HeaderMap HashMap<String ,String> hashMap, @Query("page")int page, @Query("count")int count);
 //上传头像
 @POST(Api.UPDATAHEADPIC)
 @Multipart
 Observable<UpdateHeadPicBean> updateHeadPic(@HeaderMap HashMap<String ,String> hashMap, @Part MultipartBody.Part parts);
 //添加影片用户评价
 @POST(Api.ADDMOVIECOMMENT)
 @FormUrlEncoded
 Observable<AddMovieCommentBean>  addMovieComment(@HeaderMap HashMap<String ,String> hashMap, @FieldMap Map<String, String> map);
 //添加影院用户评价
 @POST(Api.ADDCAMERACOMMENT)
 @FormUrlEncoded
 Observable<AddCimeraCommentBean>  addCameraComment(@HeaderMap HashMap<String ,String> hashMap, @FieldMap Map<String, String> map);
 //添加用户对评论的回复
 @POST(Api.COMMENTREPLAY)
 @FormUrlEncoded
 Observable<CommentReplyBean>  commentReplay(@HeaderMap HashMap<String ,String> hashMap, @FieldMap Map<String, String> map);

}