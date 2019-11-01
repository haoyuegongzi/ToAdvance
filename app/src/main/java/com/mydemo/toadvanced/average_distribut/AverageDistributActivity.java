package com.mydemo.toadvanced.average_distribut;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.mydemo.toadvanced.BaseActivity;
import com.mydemo.toadvanced.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AverageDistributActivity extends BaseActivity {

    @BindView(R.id.rvList)
    RecyclerView rvList;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_distribut);
        ButterKnife.bind(this);
        for (int i = 0; i < 10; i++) {
            list.add(1 + i * i + "");
        }

        //第二个构造参数表示列还是行
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.VERTICAL);
        //滑动方向
        manager.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
        rvList.setLayoutManager(manager);

        AverageAdapter adapter = new AverageAdapter(this, list);
        rvList.setAdapter(adapter);
    }
}
