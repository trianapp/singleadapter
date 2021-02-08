package com.trian.singleadapter;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SingleGridLayoutManager extends GridLayoutManager {
    private int columnWidth;
    private boolean columnWidthChanged = true;

    public SingleGridLayoutManager(Context context, int columnWidth) {
        super(context, 1);

    }

    public void setColumnWidth(int newCloumnWidth) {
        if (newCloumnWidth > 0 && newCloumnWidth != columnWidth) {
            columnWidth = newCloumnWidth;
            columnWidthChanged = true;
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (columnWidthChanged && columnWidth > 0) {
            int totalSpace;
            if (getOrientation() == VERTICAL) {
                totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
            } else {
                totalSpace = getWidth() - getPaddingTop() - getPaddingBottom();

            }
            int spanCount = Math.max(1, totalSpace / columnWidth);
            setSpanCount(spanCount);
            columnWidthChanged = false;
        }
        super.onLayoutChildren(recycler, state);
    }
}
