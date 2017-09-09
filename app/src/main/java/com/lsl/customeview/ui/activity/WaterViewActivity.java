package com.lsl.customeview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lsl.customeview.R;
import com.lsl.customeview.widget.WaterView;

/**
 * 创建者： lsl
 * 创建时间： 2017/9/9 14:03
 * 描述：水面上升
 * 修改人：
 * 修改时间：
 * 备注：
 */
public class WaterViewActivity extends AppCompatActivity {

    private WaterView mWaterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_view);
        mWaterView = (WaterView) findViewById(R.id.wv);
        mWaterView.shake();
    }
}
