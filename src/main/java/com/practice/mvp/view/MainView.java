package com.practice.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.practice.mvp.R;
import com.practice.mvp.adapter.BasicAdapter;
import com.practice.mvp.contract.MainContract;
import com.practice.mvp.contract.MainContract.*;

/**
 * Created by JisangYou on 2017-11-23.
 */

public class MainView implements MainContract.iView{ // 뷰를 관리하는 곳이므로 해당 View Interface를 implement한다.

   MainContract.iPresenter presenter; // presenter를 사용하는 이유는 View와 모델간, View와 View간에 상호작용을 위함이다.

    private Context context;
    private View view;
    private TextView textView;
    private RecyclerView recyclerView;
    private BasicAdapter basicAdapter;

    public MainView(Context context){ // 이 메소드가 호출되면 해당 context자원을 받아 inflate를 해준다.
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
        initView();
        setAdapter();
    }

    /**
     * View 초기화
     */
    private void initView() {
        textView = view.findViewById(R.id.textView);
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    /**
     * RecyclerView 와 Adapter Setting
     */
    private void setAdapter() { // adapter도 item들을 가지고 있는 일종의 뷰 이므로 이곳에서 처리한다.
        basicAdapter = new BasicAdapter(context);
        recyclerView.setAdapter(basicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public View getView() { // 이 메소드가 호출되면 inflate 해준 뷰를 리턴해준다.
        return view;
    }

    /**
     * Presenter 연결
     * Presenter 에 Adapter 연결 및
     * Presenter 에 Item Load 요청
     * +
     * 이 MainView 클래스는 View를 세팅하는 동시에, presenter를 통해서 RecyclerView를 세팅하기 위한 로직이 필요
     * @param presenter
     */
    @Override
    public void setPresenter(MainContract.iPresenter presenter) { // 이미 정의가 되어 있었던 부분을 오버라이드 함.
        this.presenter = presenter;
        presenter.setBasicAdapterView(basicAdapter);    // View와
        presenter.setBasicAdapterModel(basicAdapter);   // 모델을 중간에서 제어한다.

        presenter.loadItem(); // 프레젠터에서 모델에 정의된 데이터를 받아옴으로써, 해당 아이템으로 리싸이클러뷰가 세팅이 된다.
    }

    @Override
    public void setText(String text) {
        textView.setText(text); // 프레젠터에서 받아온 text
    }
}