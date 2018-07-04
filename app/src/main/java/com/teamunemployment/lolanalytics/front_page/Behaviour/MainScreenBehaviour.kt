package com.teamunemployment.lolanalytics.front_page.Behaviour

import android.content.Context
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout

import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.AnimationUtils
import com.mikhaellopez.circularimageview.CircularImageView
import com.teamunemployment.lolanalytics.R
import java.lang.Math.max
import java.lang.Math.min

/**
 * Created by Josiah Kendall
 *
 * Navigation for the main screen behaviour
 */
class MainScreenBehaviour<V : View>constructor(context: Context, attrs: AttributeSet)  :
        CoordinatorLayout.Behavior<V>(context, attrs){

    // todo with tests
//    private val presenter by context.inject<BehaviourPresenter>()

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout?, child: V, directTargetChild: View?, target: View?, nestedScrollAxes: Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout?, child: V, target: View?, dx: Int, dy: Int, consumed: IntArray?) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed)

        when(child) {
            is CircularImageView -> {       moveFABOnScroll(dy > 0, child, dy) }
            is BottomNavigationView -> {
                val trans = max(0f, min(child.height.toFloat(), child.translationY + dy))
                Log.d("Behaviour", "translation value is: " + trans)
                child.translationY = trans}
        }
    }

    private fun moveFABOnScroll(down: Boolean, view : View, dy : Int) {

        // Scroll it down at the same rate as the bottom navigation
        val translation = max(0f, min(view.height.toFloat(), view.translationY + dy))

        view.translationY = translation
        if (down && translation > 111.0f) {
            if (view.visibility == VISIBLE) {
                val animation = AnimationUtils.loadAnimation(view.context, R.anim.fab_shrink)
                view.startAnimation(animation)
                view.visibility = INVISIBLE
            }
        } else if (!down){
            if (view.visibility == INVISIBLE) {
                view.visibility = VISIBLE
                val animation = AnimationUtils.loadAnimation(view.context, R.anim.fab_grow)
                view.startAnimation(animation)
            }

        }
    }
}