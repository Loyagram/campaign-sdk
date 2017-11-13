package com.loyagram.android.campaignsdk.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class AnimationCheckMark extends View {

    private static final int DEFAULT_LINE_WIDTH    = 4;
    private static final int DEFAULT_LINE_COLOR    = Color.WHITE;
    private static final int DEFAULT_CHECKED_COLOR = Color.parseColor("#1abc9c");
    private static final int DEFAULT_UNCHECK_COLOR = Color.parseColor("#1abc9c");
    private static final int DEFAULT_ANIM_DURATION = 150;
    private static       int DEFAULT_RADIUS        = 0;
    private Paint mCirclePaint;
    private Paint mLinePaint;

    private int radius;
    private int width, height;
    private int cx, cy;
    private float[] points = new float[6];
    private float   correctProgress;
    private boolean isChecked;
    private boolean isAnim;

    private int animDuration = DEFAULT_ANIM_DURATION;
    private int unCheckColor = DEFAULT_UNCHECK_COLOR;
    private int circleColor  = DEFAULT_CHECKED_COLOR;
    private int correctColor = DEFAULT_LINE_COLOR;
    private int correctWidth = DEFAULT_LINE_WIDTH;

    private OnCheckedChangeListener listener;

    public AnimationCheckMark(Context context) {
        super(context);
        init();
    }

    public AnimationCheckMark(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    private void init() {

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(circleColor);
        mCirclePaint.setAntiAlias(true);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setColor(correctColor);
        mLinePaint.setStrokeWidth(correctWidth);

    }

    public void setCircleColor(int color) {
        circleColor = color;
    }


    public void setLineColor(int color) {
        mLinePaint.setColor(color);
    }

    public void setUnCheckColor(int color) {
        unCheckColor = color;
    }

    public void setCorrectColor(int correctColor) {
        this.correctColor = correctColor;
    }

    public void setCorrectWidth(int correctWidth) {
        this.correctWidth = correctWidth;
    }

    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = width = Math.min(w - getPaddingLeft() - getPaddingRight(), h - getPaddingBottom() - getPaddingTop());
        cx = w / 2;
        cy = h / 2;

        float r = height / 2f;
        points[0] = r / 2f + getPaddingLeft();
        points[1] = r + getPaddingTop();

        points[2] = r * 5f / 6f + getPaddingLeft();
        points[3] = r + r / 3f + getPaddingTop();

        points[4] = r * 1.5f + getPaddingLeft();
        points[5] = r - r / 3f + getPaddingTop();
        // DEFAULT_RADIUS = radius = (int) (height * 0.125f);
        DEFAULT_RADIUS = radius = 0;
    }

    @Override protected void onDraw(Canvas canvas) {

        float f = (radius - height * 0.125f) / (height * 0.5f);
        mCirclePaint.setColor(evaluate(f, unCheckColor, circleColor));
        canvas.drawCircle(cx, cy, radius, mCirclePaint);

        if (correctProgress > 0) {
            if (correctProgress < 1 / 3f) {
                float x = points[0] + (points[2] - points[0]) * correctProgress;
                float y = points[1] + (points[3] - points[1]) * correctProgress;
                canvas.drawLine(points[0], points[1], x, y, mLinePaint);
            } else {
                float x = points[2] + (points[4] - points[2]) * correctProgress;
                float y = points[3] + (points[5] - points[3]) * correctProgress;
                canvas.drawLine(points[0], points[1], points[2], points[3], mLinePaint);
                canvas.drawLine(points[2], points[3], x, y, mLinePaint);
            }
        }
    }

    private int evaluate(float fraction, int startValue, int endValue) {
        if(fraction<=0){
            return startValue;
        }
        if(fraction>=1){
            return endValue;
        }
        int startA = (startValue >> 24) & 0xff;
        int startR = (startValue >> 16) & 0xff;
        int startG = (startValue >> 8) & 0xff;
        int startB = startValue & 0xff;

        int endA = (endValue >> 24) & 0xff;
        int endR = (endValue >> 16) & 0xff;
        int endG = (endValue >> 8) & 0xff;
        int endB = endValue & 0xff;

        return ((startA + (int) (fraction * (endA - startA))) << 24) | ((startR + (int) (fraction * (endR - startR))) << 16) | ((startG + (int) (fraction * (endG - startG))) << 8) | ((startB + (int) (fraction * (endB - startB))));
    }


    public void showCheck() {
        if (isAnim) {
            return;
        }
        isAnim = true;
        ValueAnimator va = ValueAnimator.ofFloat(0, 1).setDuration(animDuration);
        va.setInterpolator(new LinearInterpolator());
        va.start();
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue(); // 0f ~ 1f
                radius = (int) (value * height * 0.37f + height * 0.125f);
                if (value >= 1) {
                    isChecked = true;
                    isAnim = false;
                    if (listener != null) {
                        listener.onCheckedChanged(AnimationCheckMark.this, true);
                    }
                    showCorrect();
                }
                invalidate();
            }
        });
    }

    public void showCorrect() {
        if (isAnim) {
            return;
        }
        isAnim = true;
        ValueAnimator va = ValueAnimator.ofFloat(0, 1).setDuration(animDuration);
        va.setInterpolator(new LinearInterpolator());
        va.start();
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue(); // 0f ~ 1f
                correctProgress = value;
                invalidate();
                if (value >= 1) {
                    isAnim = false;
                }
            }
        });
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(View buttonView, boolean isChecked);
    }
}
