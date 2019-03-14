package com.bw.movie.base;

public class BasePresenter<V> {
    private V view;

    public void setView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }

    public void dettachView(){
        this.view=null;
    }

}
