package com.practice.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.practice.mvp.contract.MainContract;
import com.practice.mvp.presenter.MainPresenter;
import com.practice.mvp.view.MainView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 해당 화면에서 정의해놓은 View,Presenter interface를 선언한다.

        MainContract.iView mainView = new MainView(this); // MainView클래스에서 context자원을 받아서 뷰를 생성한다.
        MainContract.iPresenter mainPresenter = new MainPresenter();

        mainPresenter.attachView(mainView);
        mainView.setPresenter(mainPresenter);

        // View는 화면에 보여주는 역할이므로, setContentView를 통해 setting한다.
        // contet
        setContentView(mainView.getView());
    }
}