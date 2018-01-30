package com.practice.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.mvp.adapter.contract.BasicContract;
import com.practice.mvp.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JisangYou on 2018-01-30.
 */

public class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.ViewHolder> implements BasicContract.iView, BasicContract.iModel{

    private OnItemClickListener listener;
    private Context context;
    private List<String> sampleData = new ArrayList<>();

    public BasicAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void addItems(List<String> data) { // MainPresenterClass에서 data를 넘겨주었다.
        this.sampleData = data;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = sampleData.get(position);
        holder.setText(data);
    }

    @Override
    public int getItemCount() {
        return sampleData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView text1;

        ViewHolder(View itemView) {
            super(itemView);

            text1 = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(text1.getText().toString());
                }
            });
        }

        private void setText(String text){
            text1.setText(text);
        }

    }
}
