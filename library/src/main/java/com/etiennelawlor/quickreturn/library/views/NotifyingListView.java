package com.etiennelawlor.quickreturn.library.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


/**
 * Created by etiennelawlor on 6/23/14.
 */
public class NotifyingListView extends ListView {

    // region Member Variables
    private boolean mIsOverScrollEnabled = true;
    private OnScrollChangedListener mOnScrollChangedListener;
    // endregion

    // region Interfaces
    public interface OnScrollChangedListener {
        void onScrollChanged(ListView who, int l, int t, int oldl, int oldt);
    }
    // endregion

    // region Constructors
    public NotifyingListView(Context context) {
        super(context);
    }

    public NotifyingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NotifyingListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    // endregion

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(
                deltaX,
                deltaY,
                scrollX,
                scrollY,
                scrollRangeX,
                scrollRangeY,
                mIsOverScrollEnabled ? maxOverScrollX : 0,
                mIsOverScrollEnabled ? maxOverScrollY : 0,
                isTouchEvent);
    }

    // region Helper Methods
    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }

    public void setOverScrollEnabled(boolean enabled) {
        mIsOverScrollEnabled = enabled;
    }

    public boolean isOverScrollEnabled() {
        return mIsOverScrollEnabled;
    }
    // endregion

}