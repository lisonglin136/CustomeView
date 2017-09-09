package com.lsl.customeview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lsl.customeview.R;
import com.lsl.customeview.widget.LoveLayout;

/**
 * 创建者： lsl
 * 创建时间： 2017/9/9 14:51
 * 描述：点赞飘心
 * 修改人：
 * 修改时间：
 * 备注：
 */
public class LoveActivity extends AppCompatActivity {

    private LoveLayout mLoveLayout;
    private Button mBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        mLoveLayout = (LoveLayout) findViewById(R.id.love_layout);
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoveLayout.addIcon();
            }
        });
    }
}
