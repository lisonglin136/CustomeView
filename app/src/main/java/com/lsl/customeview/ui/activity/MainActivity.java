package com.lsl.customeview.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lsl.customeview.R;
import com.lsl.customeview.adapter.MainItemAdapter;
import com.lsl.customeview.bean.MainItemBean;
import com.lsl.customeview.presenter.MainPresenter;
import com.lsl.customeview.view.IMainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //加载数据
        new MainPresenter(this).loadData();
    }


    @Override
    public void showData(List<MainItemBean> dataList) {
        MainItemAdapter adapter = new MainItemAdapter(this, dataList);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        mRecyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(new MainItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, WaterViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, FunnelActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, LoveActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
                        break;

                }
            }
        });
    }
}
