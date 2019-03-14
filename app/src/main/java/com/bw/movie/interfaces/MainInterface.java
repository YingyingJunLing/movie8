package com.bw.movie.interfaces;

import com.bw.movie.base.BaseInterface;


public interface MainInterface<T> extends BaseInterface {
    void onSuccess(T t);
    void onFail(String s);
}
