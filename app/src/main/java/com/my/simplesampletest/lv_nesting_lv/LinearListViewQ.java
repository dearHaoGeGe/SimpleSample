package com.my.simplesampletest.lv_nesting_lv;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

/**
 * LinearListView
 */
public class LinearListViewQ extends LinearLayout {
    private ListAdapter adapter;
    private DataSetObserver dataSetObserver;

    public LinearListViewQ(Context context) {
        super(context);
    }

    public LinearListViewQ(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(ListAdapter adapter) {
        if (adapter == null && dataSetObserver != null) {
            this.adapter.unregisterDataSetObserver(dataSetObserver);
            return;
        }
        if (adapter != null && dataSetObserver == null) {
            this.adapter = adapter;
            this.dataSetObserver = new LinearAdapterDataSet();
            this.adapter.registerDataSetObserver(dataSetObserver);
            this.dataSetObserver.onChanged();
        }
        requestLayout();
    }

    class LinearAdapterDataSet extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            int count = adapter.getCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                boolean add = child == null;
                View view = changedView(i, child);
                if (add && view != null) {
                    addView(view);
                }
            }
            requestLayout();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            if (getChildCount() > 0) {
                removeAllViews();
            }
            int count = adapter.getCount();
            for (int i = 0; i < count; i++) {
                View view = changedView(i, null);
                if (view == null) {
                    continue;
                }
                addView(view);
            }
            requestLayout();
        }

        private View changedView(int position, View view) {
            return adapter.getView(position, view, LinearListViewQ.this);
        }
    }

}
