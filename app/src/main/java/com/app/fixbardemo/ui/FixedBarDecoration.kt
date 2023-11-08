package com.app.fixbardemo.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class FixedBarDecoration(context: Context?) : ItemDecoration() {
    private val mItemHeaderHeight: Int
    private val mLinePaint: Paint
    private val mItemHeaderPaint: Paint
    private val mTextPaint: Paint
    private val mTextRect: Rect

    init {
        mItemHeaderHeight = ViewUtils.dip2px(context, 40f)
        mTextRect = Rect()
        mItemHeaderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mItemHeaderPaint.color = Color.BLUE
        mLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mLinePaint.color = Color.GRAY
        mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTextPaint.textSize = 46f
        mTextPaint.color = Color.WHITE
    }

    //吸顶效果的主要实现方法
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.adapter is NormalAdapter) {
            val adapter = parent.adapter as NormalAdapter?
            val position =
                (parent.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
            if (adapter!!.isHasHeader && position == 0) {
                return
            }
            //如果不是头部view 那就直接在当前第一个可见的item顶部画一个固定栏即可
//            View view = parent.findViewHolderForAdapterPosition(position).itemView;
            c.drawRect(
                0f,
                0f,
                parent.width.toFloat(),
                mItemHeaderHeight.toFloat(),
                mItemHeaderPaint
            )
            mTextPaint.getTextBounds("悬浮固定栏", 0, "悬浮固定栏".length, mTextRect)
            c.drawText(
                "悬浮固定栏", (parent.width / 2 - mTextRect.width() / 2).toFloat(),
                (
                        mItemHeaderHeight / 2 + mTextRect.height() / 2).toFloat(), mTextPaint
            )
        }
    }

    //绘制分割线和固定栏
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.adapter is NormalAdapter) {
            val adapter = parent.adapter as NormalAdapter?
            val count = parent.childCount
            for (i in 0 until count) {
                val view = parent.getChildAt(i)
                val position = parent.getChildLayoutPosition(view)
                val isFirstItem = adapter!!.isFirstItem(position)
                if (isFirstItem) {
                    c.drawRect(
                        0f,
                        (view.top - mItemHeaderHeight).toFloat(),
                        parent.width.toFloat(),
                        view.top.toFloat(),
                        mItemHeaderPaint
                    )
                    mTextPaint.getTextBounds("悬浮固定栏", 0, "悬浮固定栏".length, mTextRect)
                    c.drawText(
                        "悬浮固定栏", (parent.width / 2 - mTextRect.width() / 2).toFloat(),
                        (
                                view.top - mItemHeaderHeight + mItemHeaderHeight / 2 + mTextRect.height() / 2).toFloat(),
                        mTextPaint
                    )
                } else {
                    c.drawRect(
                        0f,
                        (view.top - 1).toFloat(),
                        parent.width.toFloat(),
                        view.top.toFloat(),
                        mLinePaint
                    )
                }
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.adapter is NormalAdapter) {
            val adapter = parent.adapter as NormalAdapter?
            val position = parent.getChildLayoutPosition(view)
            val isFirstItem = adapter!!.isFirstItem(position)
            if (isFirstItem) {
                outRect.top = mItemHeaderHeight
            } else {
                outRect.top = 1
            }
        }
    }
}