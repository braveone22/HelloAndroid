package com.example.hello.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hello.R;
import com.example.hello.adater.MyListAdapter;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    private LinearLayoutManager linearManager;
    private ArrayList<String> strList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        //查找 Recycler
        RecyclerView rv = findViewById(R.id.rv);
        //如果你知道你的RecyclerView的高度在使用期间不会变化
        // 使用此设置可以提高性能，后面会相应的文章分析为什么
        rv.setHasFixedSize(true);

        //设置一个布局管理器，所谓布局管理器就是告诉他如何显示布局，是横向还是纵向，还是其他方式
        //这里使用的是LinearLayoutManager，默认是垂直方向
        linearManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearManager);


        //构造数据
        ArrayList<String> strList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strList.add("琳琳是笨蛋，重复 " + i + "次");
        }

        //设置适配器
        rv.setAdapter(new MyListAdapter(strList, this));
    }
}
