package com.loyagram.android.campaignsdk.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.loyagram.android.campaignsdk.ui.LoyagramCampaignView;

/**
 * Class handles the animation of widget. when loading widget animates from the bottom.
 */

public class LoyagramAnimate {

    public static void animate(ViewGroup widgetContainer, View button, LoyagramCampaignView loyagramCampaignView) {

        if (button.getVisibility() == View.VISIBLE) {
            loyagramCampaignView.initAnimation(button, widgetContainer);
            widgetContainer.addView(loyagramCampaignView);
            LoyagramAnimate.slideUp(loyagramCampaignView);
            button.setVisibility(View.INVISIBLE);

        } else {
            widgetContainer.removeView(loyagramCampaignView);
            LoyagramAnimate.slideDown(loyagramCampaignView);
            button.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Animate the the view from bottom
     *
     * @param widget View to be animated
     */
    public static void slideUp(View widget) {
        TranslateAnimation slideUpaniamtion = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1f,
                Animation.RELATIVE_TO_PARENT, 0);
        slideUpaniamtion.setDuration(400);
        slideUpaniamtion.setFillAfter(true);
        widget.startAnimation(slideUpaniamtion);
    }

    /**
     * Animate the view out of view port.
     *
     * @param widget view to be animated
     */
    public static void slideDown(View widget) {
        TranslateAnimation slideDownAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1f);
        slideDownAnimation.setDuration(400);
        slideDownAnimation.setFillAfter(true);
        widget.startAnimation(slideDownAnimation);
//        widgetContainer.setVisibility(View.INVISIBLE);
    }

}
