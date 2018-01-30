package com.practice.mvp.presenter;

import com.practice.mvp.adapter.contract.BasicContract;
import com.practice.mvp.contract.MainContract;
import com.practice.mvp.contract.MainContract.*;
import com.practice.mvp.data.SampleData;
import com.practice.mvp.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by pc on 23/11/2017.
 */

public class MainPresenter implements MainContract.iPresenter, OnItemClickListener { // View와 Model들간에 필요한 로직들을 중간에서 처리해주는 역할
                                                                                    // click리스너라는 인터페이스를 정의해서, 클릭시 이벤트 처리. event여서 presenter에서 처리

    private MainContract.iView view;
    private BasicContract.iView adapterView;
    private BasicContract.iModel adapterModel;

    @Override
    public void attachView(MainContract.iView view) { // 이 함수는 MainActivity에서 호출이 되었고 그곳에서 View를 세팅한 것을 인자로 넣었다.
                                                      // 이미 View가 세팅이 되서 넘어와서, 이곳에서 그 View를 가지고 로직을 처리할 수 있다.
        this.view = view;
    }

    @Override
    public void loadItem() {
        List<String> data = SampleData.getData();
        adapterModel.addItems(data);
        adapterView.notifyAdapter();// notifydatasetChanged를 호출하는 메소드
    }

    @Override
    public void setBasicAdapterModel(BasicContract.iModel model) { // data item list가 세팅이 된 상태로 넘어와서 처리한다.
        this.adapterModel = model;
    }

    @Override
    public void setBasicAdapterView(BasicContract.iView view) { // attachView와 마찬가지로 리싸이클러뷰에 뿌려질 itemView들이 세팅이 되어서 넘어와서 이곳에서 재정의해서, 처리한다.
        this.adapterView = view;
        this.adapterView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(String text) { // 클릭이벤트가 일어나면, attachView에서 넘어온 View에 세팅을 한다.
        view.setText(text);
    }
}
