package com.bw.movie.mvp.view.contract;

import java.util.HashMap;

public class Contract {

    /**
     * 登录的接口
     */
    public interface ILoginView{

        void onILoginSuccess(Object o);

        void onILoginFail(String errorInfo);

    }
    public interface ILoginPre{

        void onILoginPre(String url, HashMap<String, String> hashMap);

    }
    public interface ILoginModel{
        void ILogin(String url, HashMap<String, String> hashMap, LoginCallBack loginCallBack);
    }

    public interface LoginCallBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }


    //首页电影模块需要的接口
    public interface ICinemaView{

        void onICinemaSuccess(Object o);
        void onIReleaseMovieSuccess(Object o);
        void onIComingSoonMovieSuccess(Object o);

        void onICinemaFail(String errorInfo);

    }
    public interface ICinemaPre{

        void onICinemaPre(int page,int count);
        void onIReleaseMovie(int page,int count);
        void onIComingSoonMovie(int page,int count);

    }
    public interface ICinemaModel{
        void ICinema(int page,int count,CinemaCallBack cinemaCallBack);
        void IReleaseMovie(int page,int count,CinemaCallBack cinemaCallBack);
        void IComingSoonMovie(int page,int count,CinemaCallBack cinemaCallBack);
    }

    public interface CinemaCallBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }

    //首页影院模块需要的接口
    public interface IFilmView{

        void onIFilmSuccess(Object o);

        void onIFilmFail(String errorInfo);

    }
    public interface IFilmPre{

        void onIFilmPre();

    }
    public interface IFilmModel{
        void onIFilm();
    }

    public interface FilmCallBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }

    //首页我的模块需要的接口
    public interface IMyView{

        void onIMySuccess(Object o);

        void onIMyFail(String errorInfo);

    }
    public interface IMyPre{

        void onIMyPre(String url);

    }
    public interface IMyModel{
        void IMy(String url);
    }

    public interface MyCallBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }

    //详情activity
    public interface IMovieDetailView{

        void onIMovieDetailSuccess(Object o);

        void onIMovieDetailFail(String errorInfo);


    }
    public interface IMovieDetailPre{
        void onIMovieDetailPre(int movieID);
        void onIMovieCommenPre(int movieId,int page,int count);
        void onIFollowMovie(int movieId,HashMap<String ,String> hashMap);
        void onICancelFollowMovie(int movieId,HashMap<String ,String> hashMap);

    }
    public interface IMovieDetailModel{
        void onIMovieDetailModel(int movieID,MovieDetailBack movieDetailBack);
        void onIMovieCommenModel(int movieId,int page,int count,MovieDetailBack movieDetailBack);
        void onIFollowMovie(int movieId,HashMap<String ,String> hashMap,MovieDetailBack movieDetailBack);
        void onICancelFollowMovie(int movieId,HashMap<String ,String> hashMap,MovieDetailBack movieDetailBack);
    }

    public interface MovieDetailBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);

    }

    //推荐影院fragment
    public interface IRecommendView{

        void onIRecommendSuccess(Object o);

        void onIRecommendFail(String errorInfo);

    }
    public interface IRecommendPre{

        void onIRecommendPre(int page,int count);

    }
    public interface IRecommendModel{
        void onIRecommendModel(int page,int count,RecommendBack recommendBack);
    }

    public interface RecommendBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }

    //附近影院fragment
    public interface INearView{

        void onINearSuccess(Object o);

        void onINearFail(String errorInfo);

    }
    public interface INearPre{

        void onINearPre(int page,int count);

    }
    public interface INearModel{
        void onINearModel(int page,int count,NearBack nearBack);
    }

    public interface NearBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }

    //影院详情activity
    public interface ICinemaListView{

        void onICinemaListSuccess(Object o);
        void onICinemaListFail(String errorInfo);

    }
    public interface ICinemaListPre{

        void onICinemaListPre(int movieID);

    }
    public interface ICinemaListModel{
        void onICinemaListModel(int movieID,CinemaListBack cinemaListBack);
    }

    public interface CinemaListBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }

    //电影详情当期
    public interface IScheduleListView{

        void onIScheduleListSuccess(Object o);
        void onIScheduleListCinemaSuccess(Object o);
        void onIScheduleListMovieSuccess(Object o);

        void onIScheduleListFail(String errorInfo);

    }
    public interface IScheduleListPre{

        void onIScheduleListPre(int cinemasId,int movieID);
        void onIScheduleListCinemaPre(int cinemasId);
        void onIScheduleListMoviePre(int movieID);

    }
    public interface IScheduleListModel{
        void onIScheduleListModel(int cinemasId,int movieID,ScheduleListBack scheduleListBack);
        void onIScheduleListCinemaModel(int cinemasId,ScheduleListBack scheduleListBack);
        void onIScheduleListMovieModel(int movieID,ScheduleListBack scheduleListBack);
    }

    public interface ScheduleListBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }
    /**
     * 根据用户id查询用户信息
     */
    public interface IMyMessageView{

        void onIMySuccess(Object o);

        void onIMyFail(String errorInfo);

    }
    public interface IMyMessagePre{

        void onIMyPre(HashMap<String, String > hashMap);

    }
    public interface IMyMessageModel{
        void IMy(HashMap<String ,String > hashMap,MyMessageCallBack myMessageCallBack);
    }

    public interface MyMessageCallBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }
    /**
     * 我的关注电影
     */
    public interface IAttentionView{

        void onIAttentionSuccess(Object o);

        void onIMyFail(String errorInfo);

    }
    public interface IAttentionPre{

        void onIAttentionMoviePre(HashMap<String, String > hashMap,int page,int count);
        void onIAttentionCameraPres(HashMap<String, String > hashMap,int page,int count);

    }
    public interface IAttentionModel{
        void IAttentionMovie(HashMap<String ,String > hashMap,int page,int count,MyAttentionCallBack myAttentionCallBack);
        void IIAttentionCameras(HashMap<String ,String > hashMap,int page,int count,MyAttentionCallBack myAttentionCallBack);
    }

    public interface MyAttentionCallBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }
}