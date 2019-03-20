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

    }
    public interface IMovieDetailModel{
        void onIMovieDetailModel(int movieID,MovieDetailBack movieDetailBack);
        void onIMovieCommenModel(int movieId,int page,int count,MovieDetailBack movieDetailBack);
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

    //根据影院ID查询该影院当前排期的电影列表
    public interface IMovieListView{

        void onIMovieListSuccess(Object o);
        void onIMovieListCinemaSuccess(Object o);
        void onIMovieListCinemaMovieSuccess(Object o);

        void onIMovieListFail(String errorInfo);

    }
    public interface IMovieListPre{

        void onIMovieListPre(int cinemasId);
        void onIMovieListCinemaPre(int cinemasId);
        void onIMovieListCinemaMoviePre(int cinemasId,int movieId);

    }
    public interface IMovieListModel{
        void onIMovieListModel(int cinemaId,MovieListBack movieListBack);
        void onIMovieListCinemaModel(int cinemaId,MovieListBack movieListBack);
        void onIMovieListCinemaMovieModel(int cinemasId,int movieId,MovieListBack movieListBack);
    }

    public interface MovieListBack{
        void onSuccess(Object o);

        void onFail(String errorInfo);
    }
}