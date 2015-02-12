package com.yzy.supercleanmaster.widget.stickyheader;


import android.view.View;

public abstract class HeaderAnimator {

    private View mHeader;
    private int mMinHeightHeader;
    private int mHeightHeader;
    private int mMaxTransaction;

    public abstract void onScroll(final int scrolledY);

    /**
     * Called by the {@link it.carlom.stikkyheader.core.StikkyHeader} to set the {@link com.yzy.supercleanmaster.widget.stickyheader.HeaderAnimator} up
     */
    void setupAnimator(final View header, final int minHeightHeader, final int heightHeader, final int maxTransaction) {
        this.mHeader = header;
        this.mMinHeightHeader = minHeightHeader;
        this.mHeightHeader = heightHeader;
        this.mMaxTransaction = maxTransaction;

        onAnimatorAttached();
        onAnimatorReady();
    }

    /**
     * Called after that the animator is attached to the header
     */
    protected abstract void onAnimatorAttached();

    /**
     * Called after that the animator is attached and ready
     */
    protected abstract void onAnimatorReady();

    public View getHeader() {
        return mHeader;
    }

    public int getMinHeightHeader() {
        return mMinHeightHeader;
    }

    public int getHeightHeader() {
        return mHeightHeader;
    }

    public int getMaxTransaction() {
        return mMaxTransaction;
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }
}
