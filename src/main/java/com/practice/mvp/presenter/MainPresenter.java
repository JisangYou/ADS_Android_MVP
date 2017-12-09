package com.practice.mvp.presenter;

import com.practice.mvp.contract.MainContract;
import com.practice.mvp.contract.MainContract.*;

/**
 * Created by pc on 23/11/2017.
 */

public class MainPresenter implements MainContract.IPresenter{
    IView view;
    public MainPresenter() {

    }
    @Override
    public void attachView(IView view) {
        this.view = view;
    }

    public void task1(){
        view.setTextView("Clicked!");
    }
}

