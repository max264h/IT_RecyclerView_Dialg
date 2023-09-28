package com.example.recyclerviewdialogdemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DialogListAdapter extends RecyclerView.Adapter<DialogListAdapter.ViewHolder> {
    private ArrayList<String> mArrayList;
    private MainActivity mainActivity;//為了要調用MainActivity的方法
    public DialogListAdapter(ArrayList<String> arrayList,MainActivity activity){
        this.mArrayList = arrayList;
        this.mainActivity = activity;
        //接收資料以及獲取Activity
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fruits_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fruits_name = itemView.findViewById(R.id.fruits_name);
        }
    }
    @NonNull
    @Override
    public DialogListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogListAdapter.ViewHolder holder, int position) {
        holder.fruits_name.setText(mArrayList.get(position));

        holder.itemView.setOnClickListener(view -> {
            mainActivity.receiveData(mArrayList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
}
