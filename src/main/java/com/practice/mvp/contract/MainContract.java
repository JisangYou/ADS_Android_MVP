package com.practice.mvp.contract;

import android.view.View;

/**
 * Created by JisangYou on 2017-11-23.
 */

public interface MainContract {
    public interface IPresenter {
        public void attachView(IView view);
        public void task1();
    }

    public interface IView {
        public void setPresenter(IPresenter presenter);
        public void setTextView(String string);
        public View getView();
    }
}
