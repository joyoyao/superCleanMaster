package com.etiennelawlor.quickreturn.library.listeners;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;

import com.etiennelawlor.quickreturn.library.R;
import com.etiennelawlor.quickreturn.library.enums.QuickReturnType;
import com.etiennelawlor.quickreturn.library.views.NotifyingScrollView;


/**
 * Created by etiennelawlor on 7/14/14.
 */
public class SpeedyQuickReturnScrollViewOnScrollChangedListener implements NotifyingScrollView.OnScrollChangedListener {

    // region Member Variables
    private QuickReturnType mQuickReturnType;
    private View mHeader;
    private View mFooter;
    private Context mContext;
    private Animation mSlideHeaderUpAnimation;
    private Animation mSlideHeaderDownAnimation;
    private Animation mSlideFooterUpAnimation;
    private Animation mSlideFooterDownAnimation;

    // endregion

    // region Constructors
    public SpeedyQuickReturnScrollViewOnScrollChangedListener(Context context, QuickReturnType quickReturnType, View headerView, View footerView){
        mContext = context;
        mQuickReturnType = quickReturnType;

        mSlideHeaderUpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.anticipate_slide_header_up);
        mSlideHeaderDownAnimation = AnimationUtils.loadAnimation(mContext, R.anim.overshoot_slide_header_down);
        mSlideFooterUpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.overshoot_slide_footer_up);
        mSlideFooterDownAnimation = AnimationUtils.loadAnimation(mContext, R.anim.anticipate_slide_footer_down);

        mHeader =  headerView;
        mFooter =  footerView;
    }
    // endregion

    @Override
    public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
        if(t<oldt){ // scrolling up
            switch (mQuickReturnType){
                case HEADER:
                    if(mHeader.getVisibility() == View.GONE){
                        mHeader.setVisibility(View.VISIBLE);
                        mHeader.startAnimation(mSlideHeaderDownAnimation);
                    }
                    break;
                case FOOTER:
                    if(mFooter.getVisibility() == View.GONE){
                        mFooter.setVisibility(View.VISIBLE);
                        mFooter.startAnimation(mSlideFooterUpAnimation);
                    }
                    break;
                case BOTH:
                    if(mHeader.getVisibility() == View.GONE){
                        mHeader.setVisibility(View.VISIBLE);
                        mHeader.startAnimation(mSlideHeaderDownAnimation);
                    }

                    if(mFooter.getVisibility() == View.GONE){
                        mFooter.setVisibility(View.VISIBLE);
                        mFooter.startAnimation(mSlideFooterUpAnimation);
                    }
                    break;
            }


        } else if (t>oldt){ // scrolling down
            switch (mQuickReturnType){
                case HEADER:
                    if(mHeader.getVisibility() == View.VISIBLE){
                        mHeader.setVisibility(View.GONE);
                        mHeader.startAnimation(mSlideHeaderUpAnimation);
                    }
                    break;
                case FOOTER:
                    if(mFooter.getVisibility() == View.VISIBLE){
                        mFooter.setVisibility(View.GONE);
                        mFooter.startAnimation(mSlideFooterDownAnimation);
                    }
                    break;
                case BOTH:
                    if(mHeader.getVisibility() == View.VISIBLE){
                        mHeader.setVisibility(View.GONE);
                        mHeader.startAnimation(mSlideHeaderUpAnimation);
                    }

                    if(mFooter.getVisibility() == View.VISIBLE){
                        mFooter.setVisibility(View.GONE);
                        mFooter.startAnimation(mSlideFooterDownAnimation);
                    }
                    break;
            }
        }
    }
}
