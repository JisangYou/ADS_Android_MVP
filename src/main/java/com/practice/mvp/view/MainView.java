package com.practice.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.practice.mvp.R;
import com.practice.mvp.contract.MainContract;
import com.practice.mvp.contract.MainContract.*;

/**
 * Created by JisangYou on 2017-11-23.
 */

public class MainView implements MainContract.IView{

    View view;
    TextView textView;
    Button button;
    IPresenter presenter;

    public MainView(Context context){
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_main,null);
        textView = view.findViewById(R.id.textView);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(listener);
        this.view = view;
    }

    public View getView(){
        return view;
    }

    @Override
    public void setPresenter(IPresenter presenter) {
        this.presenter = presenter;
    }

    public void setTextView(String string){
        textView.setText(string);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 프레젠터에 클릭이 되었다고 통보해준다.
            presenter.task1();
        }
    };
}