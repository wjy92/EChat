package com.thirdnet.echat.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * 作者：杨水强
 * 时间：2016/3/15
 */
public class AnimatorHelper {

    public static Animator createHeightAnimator(final View view, int from, int to) {
        ValueAnimator animator = ValueAnimator.ofInt(from, to);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                view.getLayoutParams().height = value;
                view.requestLayout();
            }
        });
        return animator;
    }
}
