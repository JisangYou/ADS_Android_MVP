package com.practice.mvp.adapter.contract;

import com.practice.mvp.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by JisangYou on 2018-01-30.
 */

public interface BasicContract { // 어댑터에 필요한 interface

    interface iView{
        void notifyAdapter();
        void setOnItemClickListener(OnItemClickListener listener);
    }

    interface iModel{
        void addItems(List<String> data);
    }
}
