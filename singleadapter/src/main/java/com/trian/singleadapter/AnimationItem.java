package com.trian.singleadapter;

import android.content.Context;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class AnimationItem {
    /**
    * @getAnimation
    */
    public static Animation getAnimation(SingleAnimation animte, Context context) {

            if (animte != null) {
                switch (animte) {
                    case fade_out:
                         return AnimationUtils.loadAnimation(context, android.R.anim.fade_out);

                    case slide_in_left:
                        return  AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);

                    case slide_out_right:
                        return AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);

                    case fade_in:
                    default:
                        return AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
                }

            } else {
                return AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            }

    }
}
