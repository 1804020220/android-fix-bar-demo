package com.app.fixbardemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fruitDecoration extends RecyclerView.ItemDecoration {

    private final int groupHeaderHeight;
    private final Paint headPaint;
    private final Paint textPaint;

    private final Rect textRect;

    public fruitDecoration(Context context) {
        groupHeaderHeight = dp2px(context, 100);

        headPaint = new Paint();
        headPaint.setColor(Color.GREEN);

        textPaint = new Paint();
        textPaint.setTextSize(50);
        textPaint.setColor(Color.WHITE);

        textRect = new Rect();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if (parent.getAdapter() instanceof fruitAdapter) {
            fruitAdapter adapter = (fruitAdapter) parent.getAdapter();
            //当前屏幕中item的个数
            int count = parent.getChildCount();
            // 获取当前RecyclerView距离屏幕左边的padding
            int left = parent.getPaddingLeft();
            // right == RecyclerView的宽度 - RecyclerView的右padding
            int right = parent.getWidth() - parent.getPaddingRight();
            for (int i = 0; i < count; i++) {
                //获取对应 i 的 view
                View view = parent.getChildAt(i);
                // 获取 i 的 view 的布局位置
                int position = parent.getChildLayoutPosition(view);
                // 判断是否是头部
                boolean isGroupHeader = adapter.isGroupHeader(position);
                // i 的 view 是头部并且 当前view的top位置距离屏幕的顶部还有距离
                if(isGroupHeader && view.getTop() - groupHeaderHeight - parent.getPaddingTop() >=0) {
                    c.drawRect(left, view.getTop() - groupHeaderHeight, right, view.getTop(), headPaint);
                    String groupName = adapter.getGroupName(position);
                    textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                    c.drawText(groupName, left + 20, view.getTop() - groupHeaderHeight / 2f + textRect.height() / 2f, textPaint);
                }else if(view.getTop() - groupHeaderHeight - parent.getPaddingTop() >=0){
                    //分割线
                    c.drawRect(left, view.getTop() - 4, right, view.getTop(), headPaint);
                }
            }
       }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getAdapter() instanceof fruitAdapter) {
            fruitAdapter adapter = (fruitAdapter) parent.getAdapter();
            //TODO 返回可见区域内的第一个item的position
            int position =((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
            // 获取第一个item的view
            View itemView = parent.findViewHolderForAdapterPosition(position).itemView;
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int top = parent.getPaddingTop();
            //TODO 当第二个是头部时,
            boolean isGroupHeader = adapter.isGroupHeader(position + 1);
            if (isGroupHeader) {
                int bottom = Math.min(groupHeaderHeight, itemView.getBottom() - parent.getPaddingTop());

                c.drawRect(left, top, right, top + bottom, headPaint);
                String groupName = adapter.getGroupName(position);
                textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                c.drawText(groupName, left + 20, top + bottom - groupHeaderHeight / 2f
                        + textRect.height() / 2f, textPaint);

            }else { // 如果不是头部, 即普通的itemView，则当前的头部一直固定在顶部
                c.drawRect(left, top, right, top + groupHeaderHeight, headPaint);;
                String groupName = adapter.getGroupName(position);
                textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                c.drawText(groupName, left + 20, top + groupHeaderHeight / 2f
                        + textRect.height() / 2f, textPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getAdapter() instanceof fruitAdapter){
            // parent.getAdapter()获取到当前RecyclerView的Adapter
            fruitAdapter adapter = (fruitAdapter) parent.getAdapter();
            // 获取当前view的位置
            int position = parent.getChildLayoutPosition(view);
            if (adapter.isGroupHeader(position)) {
                // 如果是头部，则预留更大的地方
                outRect.set(0, groupHeaderHeight, 0, 0);
            }else {
                outRect.set(0, 4, 0, 0);
            }

        }
    }

    private int dp2px(Context context, float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale * .5f);
    }
}
