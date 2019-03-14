package com.bw.movie.model.api;

public class Api
{
    public static final String BASE_URL="http://172.17.8.100/movieApi/user/v1/";
    //登录 POST请求
    public static final String LOGIN="login";
    //注册 POST请求
    public static final String REG="registerUser";
    //修改密码 post
    public static final String UPDATEPASS="verify/modifyUserPwd";
    //修改用户信息  post
    public static final String UPDATEMESSAGE="verify/modifyUserInfo";
    //查询用户首页信息   get
    public static final String SELECTFIRST="verify/findUserHomeInfo";
    //根据id查询用户信息  get请求
    public static final String SELECTBYID="verify/getUserInfoByUserId";
    //用户签到  get
    public static final String QIAN="verify/userSignIn";
    //购票记录  GET
    public static final String  JILU="verify/findUserBuyTicketRecordList";
    //用户上传头像  post
    public static final String HEAD="verify/uploadHeadPic";


}
