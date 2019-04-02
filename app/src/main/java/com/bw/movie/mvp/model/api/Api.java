package com.bw.movie.mvp.model.api;

public class Api {
    public static final String BASE_URL="http://mobile.bwstudent.com/";
    //登录 POST请求
    public static final String LOGIN="movieApi/user/v1/login";
    //注册 POST请求
    public static final String REG="movieApi/user/v1/registerUser";
    //修改密码 post
    public static final String UPDATEPASS="movieApi/user/v1/verify/modifyUserPwd";
    //修改用户信息  post
    public static final String UPDATEMESSAGE="movieApi/user/v1/verify/modifyUserInfo";
    //查询用户首页信息   get
    public static final String SELECTFIRST="movieApi/user/v1/verify/findUserHomeInfo";
    //根据id查询用户信息  get请求
    public static final String MyMessage="movieApi/user/v1/verify/getUserInfoByUserId";
    //用户签到  get
    public static final String QIAN="movieApi/user/v1/verify/userSignIn";
    //购票记录  GET
    public static final String  JILU="movieApi/user/v1/verify/findUserBuyTicketRecordList";
    //用户上传头像  post
    public static final String HEAD="movieApi/user/v1/verify/uploadHeadPic";
    //热门电影列表 get
    public static final String HOTMOVIE="movieApi/movie/v1/findHotMovieList";
    //上映电影列表 get
    public static final String RELEASEMOVIE="movieApi/movie/v1/findReleaseMovieList";
    //即将上映电影列表 get
    public static final String COMINGSOONMOVIE="movieApi/movie/v1/findComingSoonMovieList";
    //根据电影ID查询电影详情
    public static final String MOVIEDETAIL="movieApi/movie/v1/findMoviesDetail";
    //推荐影院
    public static final String RECOMMENDCINEMA="movieApi/cinema/v1/findRecommendCinemas";
    //附近影院
    public static final String FINDNEARCINEMA="movieApi/cinema/v1/findRecommendCinemas";
    //根据电影ID查询当前排片该电影的影院列表
    public static final String CINEMALIST="movieApi/movie/v1/findCinemasListByMovieId";
    //根据电影ID和影院ID查询电影排期列表
    public static final String SCHEDILELIST="movieApi/movie/v1/findMovieScheduleList";
    //影院详情
    public static final String CINEMAINFO="movieApi/cinema/v1/findCinemaInfo";
    //影院评论
    public static final String MOVIECOMMENT="movieApi/movie/v1/findAllMovieComment";
    //电影关注
    public static final String FOLLOWMOVIE="movieApi/movie/v1/verify/followMovie";
    //取消电影关注
    public static final String CANCELFOLLOWMOVIE="movieApi/movie/v1/verify/cancelFollowMovie";
    //我的页面关注影片
    public static final String MYATTENTIONMOCIE="movieApi/movie/v1/verify/findMoviePageList";
    //我的页面关注影院
    public static final String MYATTENTIONCAMERA="movieApi/cinema/v1/verify/findCinemaPageList";
    //根据影院ID查询该影院当前排期的电影列表
    public static final String MOVIELIST="movieApi/movie/v1/findMovieListByCinemaId";
    //用户购票记录查询列表
    public static final String MYRECORD="movieApi/user/v1/verify/findUserBuyTicketRecordList";
    //电影评论的点赞
    public static final String MOVIECOMMENTGREATE="movieApi/movie/v1/verify/movieCommentGreat";
    //意见反馈
    public static final String  RECORDFEEDBACk="movieApi/tool/v1/verify/recordFeedBack";
    //查询影院的详情
    public static final String FINDCINEMAINFO="movieApi/cinema/v1/findCinemaInfo";
    //查看影院的评价
    public static final String FINDCINEMACOMMENT="movieApi/cinema/v1/findAllCinemaComment";
    //影院评论点赞
    public static final String CINEMACOMMENTGREATE="movieApi/cinema/v1/verify/cinemaCommentGreat";
    //购票下单
    public static final String BUYMOVIE="movieApi/movie/v1/verify/buyMovieTicket";
    //微信登录
    public static final String WECHATLOGIN="movieApi/user/v1/weChatBindingLogin";
    //微信支付
    public static final String PAY="movieApi/movie/v1/verify/pay";
    //查询系统列表
    public static final String SYSMSGLIST="movieApi/tool/v1/verify/findAllSysMsgList";
    //上传头像
    public static final String UPDATAHEADPIC="movieApi/user/v1/verify/uploadHeadPic";
    //添加影片用户评论
    public static final String ADDMOVIECOMMENT="movieApi/movie/v1/verify/movieComment";
    //添加影院评论
    public static final String ADDCAMERACOMMENT="movieApi/cinema/v1/verify/cinemaComment";

}