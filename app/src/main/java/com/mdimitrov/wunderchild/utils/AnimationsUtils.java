package com.mdimitrov.wunderchild.utils;

import android.animation.Animator;
import android.view.View;
import android.view.ViewAnimationUtils;

public class AnimationsUtils {
    public static void animateRevealCenterOfClickedTarget(View viewRoot, int colorId, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setBackgroundColor(colorId);
        anim.start();
    }
}
