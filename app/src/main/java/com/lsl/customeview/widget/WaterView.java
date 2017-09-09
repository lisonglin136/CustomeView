package com.lsl.customeview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * author:Created by lsl on 2017/5/24 14:31.
 * description:battery charging waterView
 */

public class WaterView extends View {


    private int mWidth = 700;  //开始的高度
    private int mHeight = 200; //每个波浪宽度
    private int mPerWidth = 300; //每个正弦周期宽度

    private int mPerHeight = 30; //波峰高度
    private int mStartY = 700;//开始位置Y

    int startX = -mPerWidth;//开始位置X


    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        mWidth = getWidth();
        mHeight = getHeight();

        Path path = new Path();
        path.moveTo(-mPerWidth, mStartY);

        int count = mWidth / mPerWidth + 2;

        path.moveTo(startX, mStartY);


        System.out.println("执行了");
        for (int i = 0; i < count; i++) {
            path.quadTo(startX + mPerWidth / 4 + i * mPerWidth, mStartY - mPerHeight,
                    startX + mPerWidth / 2 + i * mPerWidth, mStartY);
            path.quadTo(startX + mPerWidth / 4 * 3 + i * mPerWidth, mStartY + mPerHeight,
                    startX + mPerWidth + i * mPerWidth, mStartY);

        }
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        path.close();
        canvas.drawPath(path, paint);

    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        shake();
    }

    public void shake() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (startX < 0) {
                    startX += 10;
                } else {
                    startX = -mPerWidth;
                    startX += 10;
                }

                if (mStartY < -mPerHeight) {
                    mStartY = mHeight -100;
                } else {

                    mStartY -= 2;
                }
                postInvalidate();
                System.out.println(startX);

            }
        }, 0, 100);
    }

}







