package app.trian.singleadapter

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class AnimatedItem {
    companion object{
        fun getAnimation(animationType:SingleAdapterAnimation,context: Context):Animation{
            return when(animationType){
                SingleAdapterAnimation.fade_in -> {
                    AnimationUtils.loadAnimation(context,android.R.anim.fade_in)
                }
                SingleAdapterAnimation.fade_out -> {
                    AnimationUtils.loadAnimation(context,android.R.anim.fade_out)
                }
                SingleAdapterAnimation.slide_in_left -> {
                    AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left)
                }
                SingleAdapterAnimation.slide_out_right -> {
                    AnimationUtils.loadAnimation(context,android.R.anim.slide_out_right)
                }
            }
        }
    }
}