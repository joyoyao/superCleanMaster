package com.yzy.supercleanmaster.widget.stickyheader.animator;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AnimatorBuilder {

    public static final float DEFAULT_VELOCITY_ANIMATOR = 0.5f;

    private List<AnimatorBundle> mListAnimatorBundles;

    public AnimatorBuilder() {
        mListAnimatorBundles = new ArrayList<>(2);
    }

    public static AnimatorBuilder create() {
        return new AnimatorBuilder();
    }

    public AnimatorBuilder applyScale(final View viewToScale, final Rect finalRect) {

        if (viewToScale == null) {
            throw new RuntimeException("You passed a null view");
        }

        Rect from = buildViewRect(viewToScale);
        Float scaleX = calculateScaleX(from, finalRect);
        Float scaleY = calculateScaleY(from, finalRect);

        return applyScale(viewToScale, scaleX, scaleY);
    }

    public AnimatorBuilder applyScale(final View viewToScale, float scaleX, float scaleY) {

        if (viewToScale == null) {
            throw new RuntimeException("You passed a null view");
        }

        AnimatorBundle animatorScale = AnimatorBundle.create(AnimatorBundle.TypeAnimation.SCALE, viewToScale, scaleX, scaleY);

        adjustTranslation(animatorScale);

        mListAnimatorBundles.add(animatorScale);

        return this;
    }

    /**
     * Translate the top-left point of the view to finalPoint
     * @param viewToTranslate
     * @param finalPoint
     * @return
     */
    public AnimatorBuilder applyTranslation(final View viewToTranslate, final Point finalPoint) {

        if (viewToTranslate == null) {
            throw new RuntimeException("You passed a null view");
        }

        final Point from = buildPointView(viewToTranslate);
        Float translationX = calculateTranslationX(from, finalPoint);
        Float translationY = calculateTranslationY(from, finalPoint);

        return applyTranslation(viewToTranslate, translationX, translationY);
    }

    public AnimatorBuilder applyTranslation(final View viewToTranslate, final float translateX, final float translateY) {

        if (viewToTranslate == null) {
            throw new RuntimeException("You passed a null view");
        }

        AnimatorBundle animatorTranslation = AnimatorBundle.create(AnimatorBundle.TypeAnimation.TRANSLATION, viewToTranslate, translateX, translateY);

        adjustTranslation(animatorTranslation);

        mListAnimatorBundles.add(animatorTranslation);

        return this;
    }

    public AnimatorBuilder applyFade(final View viewToFade, final float fade) {

        if (viewToFade == null) {
            throw new RuntimeException("You passed a null view");
        }

        mListAnimatorBundles.add(AnimatorBundle.create(AnimatorBundle.TypeAnimation.FADE, viewToFade, fade));

        return this;
    }

    /**
     * @param viewToParallax
     * @param velocityParallax the velocity to apply to the view in order to show the parallax effect. choose a velocity between 0 and 1 for better results
     * @return
     */
    public AnimatorBuilder applyVerticalParallax(final View viewToParallax, final float velocityParallax) {

        if (viewToParallax == null) {
            throw new RuntimeException("You passed a null view");
        }

        mListAnimatorBundles.add(AnimatorBundle.create(AnimatorBundle.TypeAnimation.PARALLAX, viewToParallax, velocityParallax * -1));

        return this;
    }

    public AnimatorBuilder applyVerticalParallax(final View viewToParallax) {

        if (viewToParallax == null) {
            throw new RuntimeException("You passed a null view");
        }

        mListAnimatorBundles.add(AnimatorBundle.create(AnimatorBundle.TypeAnimation.PARALLAX, viewToParallax, DEFAULT_VELOCITY_ANIMATOR * -1));

        return this;
    }

    private void adjustTranslation(final AnimatorBundle newAnimator) {

        AnimatorBundle animatorScale = null, animatorTranslation = null;

        for (AnimatorBundle animator : mListAnimatorBundles) {

            if (newAnimator.mView == animator.mView) {

                if (newAnimator.mTypeAnimation == AnimatorBundle.TypeAnimation.SCALE && animator.mTypeAnimation == AnimatorBundle.TypeAnimation.TRANSLATION) {

                    animatorScale = newAnimator;
                    animatorTranslation = animator;

                } else if (newAnimator.mTypeAnimation == AnimatorBundle.TypeAnimation.TRANSLATION && animator.mTypeAnimation == AnimatorBundle.TypeAnimation.SCALE) {

                    animatorScale = animator;
                    animatorTranslation = newAnimator;

                }

                if (animatorScale != null) {
                    Float translationX = (Float) animatorTranslation.mValues[0] - ((float) animatorTranslation.mView.getWidth() * (Float) animatorScale.mValues[0] / 2f);
                    Float translationY = (Float) animatorTranslation.mValues[1] - ((float) animatorTranslation.mView.getHeight() * (Float) animatorScale.mValues[1] / 2f);

                    animatorTranslation.mValues[0] = translationX;
                    animatorTranslation.mValues[1] = translationY;

                    break;
                }
            }

        }
    }

    protected void animateOnScroll(final float boundedRatioTranslationY, float translationY) {

        for (AnimatorBundle animatorBundle : mListAnimatorBundles) {

            switch (animatorBundle.mTypeAnimation) {

                case FADE:
                    animatorBundle.mView.setAlpha(boundedRatioTranslationY); //TODO performance issues?
                    break;

                case TRANSLATION:
                    animatorBundle.mView.setTranslationX((Float) animatorBundle.mValues[0] * boundedRatioTranslationY);
                    animatorBundle.mView.setTranslationY(((Float) animatorBundle.mValues[1] * boundedRatioTranslationY) - translationY);
                    break;

                case SCALE:
                    animatorBundle.mView.setScaleX(1f - (Float) animatorBundle.mValues[0] * boundedRatioTranslationY);
                    animatorBundle.mView.setScaleY(1f - (Float) animatorBundle.mValues[1] * boundedRatioTranslationY);
                    break;

                case PARALLAX:
                    animatorBundle.mView.setTranslationY((Float) animatorBundle.mValues[0] * translationY);
                    break;

                default:
                    break;

            }

        }

    }

    public boolean hasAnimatorBundles() {
        return mListAnimatorBundles.size() > 0;
    }

    public static Rect buildViewRect(final View view) {
        //TODO get coordinates related to the header
        return new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    public static Point buildPointView(final View view) {
        return new Point(view.getLeft(), view.getTop());
    }

    public static float calculateScaleX(final Rect from, final Rect to) {
        return 1f - (float) to.width() / (float) from.width();
    }

    public static float calculateScaleY(final Rect from, final Rect to) {
        return 1f - (float) to.height() / (float) from.height();
    }

    public static float calculateTranslationX(final Point from, final Point to) {
        return to.x - from.x;
    }

    public static float calculateTranslationY(final Point from, final Point to) {
        return to.y - from.y;
    }

    public static class AnimatorBundle {

        public enum TypeAnimation {
            SCALE, FADE, TRANSLATION, PARALLAX
        }

        private Object[] mValues;
        private final TypeAnimation mTypeAnimation;
        private View mView;

        AnimatorBundle(final TypeAnimation typeAnimation) {
            mTypeAnimation = typeAnimation;
        }

        public static AnimatorBundle create(final TypeAnimation typeAnimation, final View view, final Object... values) {
            AnimatorBundle animatorBundle = new AnimatorBundle(typeAnimation);

            animatorBundle.mView = view;
            animatorBundle.mValues = values;

            return animatorBundle;
        }

    }


}
