package com.lsl.customeview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.lsl.customeview.R;

/**
 * author:Created by lsl on 2017/5/3 15:40.
 * description:自定义漏斗图
 */

public class FunnelView extends View {

    private int mWidth;//控件的宽
    private int mHeight;//控件的高
    private int mTopPaintColor;//顶部梯形颜色
    private int mMiddlePaintColor;//中间梯形颜色
    private int mBottomPaintColor;//底部梯形颜色
    private int mTrapeziumTextSize;//梯形中文字
    private int dp3;
    private int dp5;
    private int dp10;
    private int dp15;
    private int dp20;
    private int dp30;
    private int dp40;
    private int dp60;
    private Paint mTopPaint; //上边梯形画笔
    private Paint mMiddlePaint;   //中间梯形画笔
    private Paint mBottomPaint;   //底部梯形画笔
    private Paint mTrapeziumTextPaint;//梯形文字画笔
    private Paint mLinkLinePaint;//连接线画笔
    private Paint mChinesepaint;//梯形外中文文字画笔
    private Paint mNumberpaint;//梯形外中文文字画笔
    private int mTrapeziumTextPaintColor;//梯形文字颜色
    private int mLinkLineColor;//连接线颜色
    private int mChineseTextColor;//梯形外中文文字颜色
    private int mNumberTextColor;
    private float mChineseTextSize;//梯形外中文文字大小
    private float mNumberTextSize;
    private String mGetPercent;
    private String mChangePercent;
    private String mUsePercent;
    private String mProvideCount;
    private String mReceiveCount;
    private String mUseCount;

    public FunnelView(Context context) {
        this(context, null);
    }

    public FunnelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FunnelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        float textSize = getContext().getResources().getDimension(R.dimen.trapeziumTextSize);
        float chineseTextSize = getContext().getResources().getDimension(R.dimen.chineseTextSize);
        float numberTextSize = getContext().getResources().getDimension(R.dimen.numberTextSize);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FunnelView);
        mTopPaintColor = typedArray.getColor(R.styleable.FunnelView_topColor, getResources()
                .getColor(R.color.top));
        mMiddlePaintColor = typedArray.getColor(R.styleable.FunnelView_middleColor,
                getResources()
                        .getColor(R.color.middle));
        mBottomPaintColor = typedArray.getColor(R.styleable.FunnelView_bottomColor,
                getResources()
                        .getColor(R.color.bottom));
        mTrapeziumTextPaintColor = typedArray.getColor(R.styleable.FunnelView_trapeziumTextColor,
                getResources()
                        .getColor(R.color.trapeziumText));
        mLinkLineColor = typedArray.getColor(R.styleable.FunnelView_linkLineColor, getResources()
                .getColor(R.color
                        .lineColor));
        mChineseTextColor = typedArray.getColor(R.styleable.FunnelView_chieseTextColor,
                getResources()
                        .getColor(R.color
                                .chineseTextColor));
        mNumberTextColor = typedArray.getColor(R.styleable.FunnelView_numberTextColor,
                getResources()
                        .getColor(R.color
                                .numberTextColor));
        mTrapeziumTextSize = (int) typedArray.getDimension(R.styleable
                .FunnelView_trapeziumTextSize, textSize);
        mChineseTextSize = typedArray.getDimension(R.styleable
                .FunnelView_chineseTextSize, chineseTextSize);
        mNumberTextSize = typedArray.getDimension(R.styleable
                .FunnelView_numberTextSize, numberTextSize);

        mGetPercent = typedArray.getString(R.styleable.FunnelView_getPercent);
        mChangePercent = typedArray.getString(R.styleable.FunnelView_changePercent);
        mUsePercent = typedArray.getString(R.styleable.FunnelView_usePercent);
        mProvideCount = typedArray.getString(R.styleable.FunnelView_provideCount);
        mReceiveCount = typedArray.getString(R.styleable.FunnelView_receiveCount);
        mUseCount = typedArray.getString(R.styleable.FunnelView_useCount);

        typedArray.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            mWidth = getWidth();
            mHeight = getHeight();
        }
    }

    private void initData() {
        //上边梯形画笔
        mTopPaint = new Paint();
        mTopPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTopPaint.setAntiAlias(true);//去锯齿
        mTopPaint.setColor(mTopPaintColor);//颜色
        //中间梯形画笔
        mMiddlePaint = new Paint();
        mMiddlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mMiddlePaint.setAntiAlias(true);//去锯齿
        mMiddlePaint.setColor(mMiddlePaintColor);//颜色
        //下边梯形画笔
        mBottomPaint = new Paint();
        mBottomPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBottomPaint.setAntiAlias(true);//去锯齿
        mBottomPaint.setColor(mBottomPaintColor);//颜色
        //梯形中文字画笔
        mTrapeziumTextPaint = new Paint();
        mTrapeziumTextPaint.setStyle(Paint.Style.STROKE);
        mTrapeziumTextPaint.setAntiAlias(true);//去锯齿
        mTrapeziumTextPaint.setColor(mTrapeziumTextPaintColor);//颜色
        mTrapeziumTextPaint.setTextSize(mTrapeziumTextSize);
        mTrapeziumTextPaint.setTextAlign(Paint.Align.CENTER);
        //连接线画笔
        mLinkLinePaint = new Paint();
        mLinkLinePaint.setStyle(Paint.Style.STROKE);
        mLinkLinePaint.setAntiAlias(true);//去锯齿
        mLinkLinePaint.setColor(mLinkLineColor);//颜色
        mLinkLinePaint.setStrokeWidth(3);
        //梯形外中文文字字画笔
        mChinesepaint = new Paint();
        mChinesepaint.setStyle(Paint.Style.STROKE);
        mChinesepaint.setAntiAlias(true);//去锯齿
        mChinesepaint.setColor(mChineseTextColor);//颜色
        mChinesepaint.setTextSize(mChineseTextSize);
        mChinesepaint.setTextAlign(Paint.Align.LEFT);
        //梯形外数字文字字画笔
        mNumberpaint = new Paint();
        mNumberpaint.setStyle(Paint.Style.STROKE);
        mNumberpaint.setAntiAlias(true);//去锯齿
        mNumberpaint.setColor(mNumberTextColor);//颜色
        mNumberpaint.setTextSize(mNumberTextSize);
        mNumberpaint.setTextAlign(Paint.Align.LEFT);

        //图形距离上方偏移量
        dp3 = (int) getContext().getResources().getDimension(R.dimen._3dp);
        dp5 = (int) getContext().getResources().getDimension(R.dimen._5dp);
        dp10 = (int) getContext().getResources().getDimension(R.dimen._10dp);
        dp15 = (int) getContext().getResources().getDimension(R.dimen._15dp);
        dp20 = (int) getContext().getResources().getDimension(R.dimen._20dp);
        dp30 = (int) getContext().getResources().getDimension(R.dimen._30dp);
        dp40 = (int) getContext().getResources().getDimension(R.dimen._40dp);
        dp60 = (int) getContext().getResources().getDimension(R.dimen._60dp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initData();
        drawTrapezium(canvas);//画梯形
        drawTrapeziumText(canvas);//画梯形中文字
        drawLinkLine(canvas);//画连接线
        drawChineseText(canvas);//画梯形外的中文文字
        drawNumberText(canvas);//画梯形外的数字
    }

    private void drawNumberText(Canvas canvas) {
        if (!TextUtils.isEmpty(mGetPercent)) {
            canvas.drawText(mGetPercent, mWidth / 4 - dp40 + dp3, mHeight / 3 * 2 - dp20,
                    mNumberpaint);
        }
        if (!TextUtils.isEmpty(mChangePercent)) {
            canvas.drawText(mChangePercent, mWidth / 4 - dp60 - dp10 + dp3, mHeight - dp20,
                    mNumberpaint);
        }
        if (!TextUtils.isEmpty(mUsePercent)) {
            canvas.drawText(mUsePercent, mWidth / 4 - dp20 + dp3, mHeight - dp20, mNumberpaint);
        }
        if (!TextUtils.isEmpty(mProvideCount)) {
            canvas.drawText(mProvideCount, mWidth / 4 * 3 - dp5 + 3 * mChineseTextSize, mHeight /
                    3 - mChineseTextSize / 2, mNumberpaint);
        }
        if (!TextUtils.isEmpty(mReceiveCount)) {
            canvas.drawText(mReceiveCount, mWidth / 4 * 3 - dp20 + 4 * mChineseTextSize, mHeight / 3
                    * 2 - mChineseTextSize / 2, mNumberpaint);
        }
        if (!TextUtils.isEmpty(mUseCount)) {
            canvas.drawText(mUseCount, mWidth / 4 * 3 - dp15 * 2 - dp5 + 4 * mChineseTextSize,
                    mHeight -
                            mChineseTextSize / 2, mNumberpaint);
        }
    }

    private void drawChineseText(Canvas canvas) {
        canvas.drawText(getContext().getString(R.string.getPercent), mWidth / 4 - dp40 + dp3,
                mHeight / 3 * 2 - dp20 - mChineseTextSize,
                mChinesepaint);
        canvas.drawText(getContext().getString(R.string.changePercent), mWidth / 4 - dp60 - dp10
                        + dp3, mHeight - dp20 - mChineseTextSize,
                mChinesepaint);

        canvas.drawText(getContext().getString(R.string.usePercent), mWidth / 4 - dp20 + dp3,
                mHeight - dp20 - mChineseTextSize,
                mChinesepaint);

        canvas.drawText(getContext().getString(R.string.provideCount), mWidth / 4 * 3 - dp5,
                mHeight / 3 - mChineseTextSize / 2 * 3 -
                        dp3,
                mChinesepaint);
        canvas.drawText(getContext().getString(R.string.receiveCount), mWidth / 4 * 3 - dp20,
                mHeight / 3 * 2 - mChineseTextSize /
                        2 * 3 - dp3, mChinesepaint);
        canvas.drawText(getContext().getString(R.string.useCount), mWidth / 4 * 3 - dp15 * 2 -
                dp5, mHeight - mChineseTextSize
                / 2 * 3 - dp3, mChinesepaint);


    }

    private void drawLinkLine(Canvas canvas) {
        Path leftpath1 = new Path();
        leftpath1.moveTo(mWidth / 4 * 3, 0);
        leftpath1.lineTo(mWidth / 4 - dp60 - dp10, 0);
        leftpath1.lineTo(mWidth / 4 - dp60 - dp10, mHeight);
        leftpath1.lineTo(mWidth / 4 - dp30, mHeight);
        canvas.drawPath(leftpath1, mLinkLinePaint);

        Path leftpath2 = new Path();
        leftpath2.moveTo(mWidth / 4 - dp40, 0);
        leftpath2.lineTo(mWidth / 4 - dp40, mHeight / 3 * 2);
        leftpath2.lineTo(mWidth - dp20, mHeight / 3 * 2);
        canvas.drawPath(leftpath2, mLinkLinePaint);

        Path leftpath3 = new Path();
        leftpath3.moveTo(mWidth / 4 - dp20, mHeight / 3 * 2);
        leftpath3.lineTo(mWidth / 4 - dp20, mHeight);
        leftpath3.lineTo(mWidth - dp20, mHeight);
        canvas.drawPath(leftpath3, mLinkLinePaint);

        Path rightPath = new Path();
        rightPath.moveTo(mWidth / 4 + dp15, mHeight / 3);
        rightPath.lineTo(mWidth - dp20, mHeight / 3);
        canvas.drawPath(rightPath, mLinkLinePaint);

    }

    private void drawTrapeziumText(Canvas canvas) {
        canvas.drawText("发放量", mWidth / 2, mHeight / 6 + mTrapeziumTextSize / 2,
                mTrapeziumTextPaint);
        canvas.drawText("领券量", mWidth / 2, mHeight / 2 + mTrapeziumTextSize / 2,
                mTrapeziumTextPaint);
        canvas.drawText("使用量", mWidth / 2, mHeight / 6 * 5 + mTrapeziumTextSize / 2,
                mTrapeziumTextPaint);
    }

    private void drawTrapezium(Canvas canvas) {
        Path topPath = new Path();
        topPath.moveTo(mWidth / 4, 0);
        topPath.lineTo(mWidth / 4 * 3, 0);
        topPath.lineTo(mWidth / 4 * 3 - dp15, mHeight / 3);
        topPath.lineTo(mWidth / 4 + dp15, mHeight / 3);
        topPath.lineTo(mWidth / 4, 0);
        canvas.drawPath(topPath, mTopPaint);

        Path middlePath = new Path();
        middlePath.moveTo(mWidth / 4 + dp15, mHeight / 3);
        middlePath.lineTo(mWidth / 4 + dp15 * 2, mHeight / 3 * 2);
        middlePath.lineTo(mWidth / 4 * 3 - dp15 * 2, mHeight / 3 * 2);
        middlePath.lineTo(mWidth / 4 * 3 - dp15, mHeight / 3);
        canvas.drawPath(middlePath, mMiddlePaint);

        Path bottomPath = new Path();
        bottomPath.moveTo(mWidth / 4 + dp15 * 2, mHeight / 3 * 2);
        bottomPath.lineTo(mWidth / 4 + dp15 * 3, mHeight);
        bottomPath.lineTo(mWidth / 4 * 3 - dp15 * 3, mHeight);
        bottomPath.lineTo(mWidth / 4 * 3 - dp15 * 2, mHeight / 3 * 2);
        canvas.drawPath(bottomPath, mBottomPaint);
    }


    /**
     * 设置达到率
     *
     * @param mGetPercent 达到率百分比
     */
    public void setGetPercent(String mGetPercent) {
        this.mGetPercent = mGetPercent;

    }

    /**
     * 设置转换率
     *
     * @param mChangePercent 转换率百分比
     */
    public void setChangePercent(String mChangePercent) {
        this.mChangePercent = mChangePercent;
    }

    /**
     * 设置使用率
     *
     * @param mUsePercent 使用率百分比
     */
    public void setUsePercent(String mUsePercent) {
        this.mUsePercent = mUsePercent;
    }

    /**
     * 设置优惠券发放数量
     *
     * @param mProvideCount 优惠券发放数量
     */
    public void setProvideCount(String mProvideCount) {
        this.mProvideCount = mProvideCount;
    }

    /**
     * 设置会员领取优惠券数量
     *
     * @param mReceiveCount 会员领取优惠券数量
     */
    public void setReceiveCount(String mReceiveCount) {
        this.mReceiveCount = mReceiveCount;
    }

    /**
     * 设置会员使用优惠券数量
     *
     * @param mUseCount 会员使用优惠券数量
     */
    public void setUseCount(String mUseCount) {
        this.mUseCount = mUseCount;
    }
}
