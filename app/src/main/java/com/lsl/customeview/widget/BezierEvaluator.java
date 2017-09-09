package com.lsl.customeview.widget;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * 创建者： lsl
 * 创建时间： 2017/8/23 15:05
 * 描述：估值器
 * 修改人：
 * 修改时间：
 * 备注：
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF pointF1;
    private PointF pointF2;

    public BezierEvaluator(PointF pointF1, PointF pointF2) {
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float t, PointF pointF0, PointF pointF3) {
        //t百分比：0~1
        // b(t)=p0*(1-t)*(1-t)*(1-t)+3*p1*t*(1-t)*(1-t)+3*p2*t*t*(1-t)+p3*t*t*t
        PointF pointF = new PointF();
        pointF.x = pointF0.x * (1 - t) * (1 - t) * (1 - t) + 3 * pointF1.x * t * (1 - t) * (1 - t) +
                3 * pointF2.x * t * t * (1 - t)
                + pointF3.x * t * t * t;
        pointF.y = pointF0.y  * (1 - t) * (1 - t) * (1 - t) + 3 * pointF1.y  * t * (1 - t) * (1 - t) +
                3 * pointF2.y  * t * t * (1 - t)
                + pointF3.y  * t * t * t;

        return pointF;
    }
}
