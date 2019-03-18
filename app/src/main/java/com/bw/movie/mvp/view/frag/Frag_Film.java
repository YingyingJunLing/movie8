package com.bw.movie.mvp.view.frag;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.mvp.presenter.presenterimpl.FilmPresenter;
import com.bw.movie.mvp.view.base.BaseFragment;
import com.bw.movie.mvp.view.contract.Contract;

/**
 * @author zhangbo
 * 影院
 */
public class Frag_Film extends BaseFragment<Contract.IFilmView, FilmPresenter> implements Contract.IFilmView {

    private View view;
    private Frag_Film_Recommend frag_film_recommend;
    private Frag_Film_Near frag_film_near;
    private FragmentManager manager;
    private RadioGroup filmGroup;
    private RadioButton frag_film_recommend_btn;
    private RadioButton frag_film_near_btn;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        view = View.inflate(getActivity(), R.layout.frag_film, null);
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        filmGroup = view.findViewById(R.id.film_group);
        frag_film_recommend_btn = view.findViewById(R.id.frag_film_recommend_btn);
        frag_film_near_btn = view.findViewById(R.id.frag_film_near_btn);
        frag_film_recommend_btn.setChecked(true);
        manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        frag_film_recommend = new Frag_Film_Recommend();
        frag_film_near = new Frag_Film_Near();
        transaction.add(R.id.fragment,frag_film_recommend);
        transaction.add(R.id.fragment,frag_film_near);
        transaction.show(frag_film_recommend).hide(frag_film_near);
        transaction.commit();
        filmGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.frag_film_recommend_btn:
                        transaction.show(frag_film_recommend).hide(frag_film_near);
                        break;
                    case R.id.frag_film_near_btn:
                        transaction.show(frag_film_near).hide(frag_film_recommend);
                        break;
                }
                transaction.commit();
            }
        });
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {

    }

    @Override
    protected FilmPresenter createPresenter() {
        return null;
    }

    @Override
    public void onIFilmSuccess(Object o) {

    }

    @Override
    public void onIFilmFail(String errorInfo) {

    }
}
