package com.yzy.supercleanmaster.widget.stickyheader.animator;

public class HeaderStikkyAnimator extends BaseStickyHeaderAnimator {

    private float mBoundedTranslatedRatio;

    protected AnimatorBuilder mAnimatorBuilder;

    private boolean hasAnimatorBundles = false;

    @Override
    protected void onAnimatorReady() {
        super.onAnimatorReady();
        mAnimatorBuilder = getAnimatorBuilder();
        hasAnimatorBundles = (mAnimatorBuilder != null) && (mAnimatorBuilder.hasAnimatorBundles());
    }

    /**
     * Override if you want to load the animator builder
     */
    public AnimatorBuilder getAnimatorBuilder() {
        return null;
    }

    @Override
    public void onScroll(int scrolledY) {
        super.onScroll(scrolledY);

        mBoundedTranslatedRatio = clamp(getTranslationRatio(), 0f, 1f);

        if (hasAnimatorBundles) {
            mAnimatorBuilder.animateOnScroll(mBoundedTranslatedRatio, getHeader().getTranslationY());
        }

    }

    public float getBoundedTransletedRatio() {
        return mBoundedTranslatedRatio;
    }
}
