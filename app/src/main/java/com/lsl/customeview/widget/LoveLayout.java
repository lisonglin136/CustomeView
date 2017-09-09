package com.lsl.customeview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lsl.customeview.R;

import java.util.Random;

/**
 * 创建者： lsl
 * 创建时间： 2017/8/23 14:11
 * 描述：
 * 修改人：
 * 修改时间：
 * 备注：
 */
public class LoveLayout extends RelativeLayout {

    private Interpolator line = new LinearInterpolator();//线性
    private Interpolator acc = new AccelerateInterpolator();//加速
    private Interpolator dce = new DecelerateInterpolator();//减速
    private Interpolator accdec = new AccelerateDecelerateInterpolator();//先加速后减速
    private Interpolator[] interpolators ;

    private int mWidth;
    private int mHeight;
    Drawable[] drawables = new Drawable[3];
    private int dHeight;
    private int dWidth;
    private LayoutParams params;
    private Random random = new Random();

    public LoveLayout(Context context) {
        this(context,null);
    }

    public LoveLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        interpolators = new Interpolator[4];
        interpolators[0] =line;
        interpolators[1] =acc;
        interpolators[2] =dce;
        interpolators[3] =accdec;

        //准备图片集合
        drawables[0] = getResources().getDrawable(R.mipmap.red);
        drawables[1] = getResources().getDrawable(R.mipmap.yellow);
        drawables[2] = getResources().getDrawable(R.mipmap.blue);

        //得到图片的原始高度
        dWidth = drawables[0].getIntrinsicWidth();
        dHeight = drawables[0].getIntrinsicHeight();
        params = new LayoutParams(dWidth, dHeight);
        //将iv添加到父容器底部、水平居中位置
        params.addRule(CENTER_HORIZONTAL);
        params.addRule(ALIGN_PARENT_BOTTOM);

    }

    public void addIcon(){
        //添加心形，并开始执行动画
        final ImageView iv = new ImageView(getContext());
        iv.setImageDrawable(drawables[random.nextInt(3)]);
        //将iv添加到父容器底部、水平居中位置
        iv.setLayoutParams(params);
        addView(iv);
        //开始属性动画：平移、透明度渐变、缩放动画
        AnimatorSet set = getAnimator(iv);

        //监听动画执行完毕，将iv移除或者复用
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(iv);
            }
        });
        //开启动画
        set.start();
    }

    // 得到一个iv的动画集合
    private AnimatorSet getAnimator(ImageView iv){
        //平移、透明度渐变、缩放动画
        //1.alpha动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(iv, "alpha", 0.3f, 1f);
        //2.缩放动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv, "scaleX", 0.3f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv, "scaleY", 0.3f, 1f);
        //三个动画同时执行
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(600);
        animatorSet.playTogether(alpha,scaleX,scaleY);

        //设置平移的曲线动画---贝塞尔曲线
        ValueAnimator bezierAnimator = getBezierValueAnimator(iv);

        AnimatorSet set = new AnimatorSet();
        //按序列执行
        set.playSequentially(animatorSet,bezierAnimator);
        set.setTarget(iv);
        return set;
    }

    //得到一个贝塞尔曲线动画
    private ValueAnimator getBezierValueAnimator(final ImageView iv) {
        //根据贝塞尔公式确定四个点（起始点p0，拐点1p1，拐点2p2，终点p3）
        PointF pointF0 = new PointF((mWidth - dWidth) / 2, mHeight - dHeight);
        PointF pointF3 = new PointF(random.nextInt(mWidth), 0);
        PointF pointF1 = getPointF(1);
        PointF pointF2 = getPointF(2);
        //估值器Evaluator,来控制view的行驶路径(不断地修改point.x,point.y)
        BezierEvaluator bezierEvaluator = new BezierEvaluator(pointF1, pointF2);
        //属性动画不仅仅可以改变view的属性，还可以改变自定义的属性(比如Point)
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierEvaluator, pointF0, pointF3);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                iv.setX(pointF.x);
                iv.setY(pointF.y);
                iv.setAlpha(1-animation.getAnimatedFraction());
            }
        });
        valueAnimator.setDuration(4000);
        return valueAnimator;
    }

    private PointF getPointF(int i) {
        PointF pointF = new PointF();
        pointF.x = random.nextInt(mWidth);
        //为了好看，尽量保证point2.y>point1.y
        if (i == 1) {
            pointF.y = random.nextInt(mHeight / 2) + mHeight / 2;
        } else {
            pointF.y = random.nextInt(mHeight / 2);
        }
        return pointF;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }
}
