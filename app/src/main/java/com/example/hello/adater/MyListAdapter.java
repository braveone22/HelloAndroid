package com.example.hello.adater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

    private final ArrayList<String> strList;
    private final Context context;
    private final LayoutInflater inflater;

    public MyListAdapter(ArrayList<String> arr, Context context) {
        strList = arr;
        this.context = context;
        //对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.inflate()来载入；
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //注意这里，一定要将parent传递到inflate方法
        return new MyViewHolder(inflater.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.MyViewHolder holder, int position) {
        holder.bindData(strList.get(position));
    }

    @Override
    public int getItemCount() {
        return strList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tx;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tx = itemView.findViewById(android.R.id.text1);
        }

        public void bindData(String s) {
            tx.setText(s);
        }
    }
}
