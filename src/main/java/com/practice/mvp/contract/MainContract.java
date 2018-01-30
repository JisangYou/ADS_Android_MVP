package com.practice.mvp.contract;

import android.view.View;

import com.practice.mvp.adapter.contract.BasicContract;

/**
 * Created by JisangYou on 2017-11-23.
 */

public interface MainContract { // 이곳에서 View, Presenter interface를 정의함.

    interface iView{  // 화면에 보여지는 View 담당 및 Presenter와 상호작용
        /**
         * View 를 반환하는 메소드
         * @return View
         */
        View getView();

        /**
         * Presenter 연결 메소드
         * @param presenter
         */
        void setPresenter(iPresenter presenter);

        /**
         * Text 변경 메소드
         * @param text
         */
        void setText(String text);
    }

    interface iPresenter{ // View와 Model 중간에서 로직을 처리하는 중계자 역할
                          // 그렇기 때문에, View와 연결하는 것, 정의해놓은 model 아이템들을 불러오는 것 등이 이슈이다.

        /**
         * View 연결 메소드
         * @param view
         */
        void attachView(iView view);

        /**
         * Item 불러오는 메소드
         */
        void loadItem();
        // BasicAdapter 의 Model(data) 와 상호작용

        /**
         * BasicAdapter 와 연결(Model)
         * @param model
         */
        void setBasicAdapterModel(BasicContract.iModel model);

        /**
         * BasicAdapter 와 연결(View)
         * @param view
         */
        void setBasicAdapterView(BasicContract.iView view);
    }
}
