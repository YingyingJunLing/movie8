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
    public static final String SELECTBYID="movieApi/user/v1/verify/getUserInfoByUserId";
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

}
