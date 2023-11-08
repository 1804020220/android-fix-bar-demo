package com.app.fixbardemo.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.coordinatorlayout.widget.CoordinatorLayout

/**
 * @Description:
 * @author gk
 * @date 2023/11/7
 */
class ColorBehavior(val context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<AppCompatImageView>() {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: AppCompatImageView,
        dependency: View
    ): Boolean {
        return super.layoutDependsOn(parent, child, dependency)
    }


    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: AppCompatImageView,
        dependency: View
    ): Boolean {
        return super.onDependentViewChanged(parent, child, dependency)
    }
}