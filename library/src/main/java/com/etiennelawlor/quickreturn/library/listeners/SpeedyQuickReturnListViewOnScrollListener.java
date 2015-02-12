package com.etiennelawlor.quickreturn.library.listeners;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;


import com.etiennelawlor.quickreturn.library.R;
import com.etiennelawlor.quickreturn.library.enums.QuickReturnType;
import com.etiennelawlor.quickreturn.library.utils.QuickReturnUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 7/14/14.
 */
public class SpeedyQuickReturnListViewOnScrollListener implements AbsListView.OnScrollListener {

    // region Member Variables
    private View mHeader;
    private View mFooter;
    private ArrayList<View> mHeaderViews;
    private ArrayList<View> mFooterViews;
    private int mPrevScrollY = 0;
    private QuickReturnType mQuickReturnType;
    private Context mContext;
    private Animation mSlideHeaderUpAnimation;
    private Animation mSlideHeaderDownAnimation;
    private Animation mSlideFooterUpAnimation;
    private Animation mSlideFooterDownAnimation;
    private List<AbsListView.OnScrollListener> mExtraOnScrollListeners = new ArrayList<AbsListView.OnScrollListener>();
    // endregion

    // region Constructors
    public SpeedyQuickReturnListViewOnScrollListener(Context context, QuickReturnType quickReturnType, View headerView, View footerView){
        mContext = context;
        mQuickReturnType = quickReturnType;

        mSlideHeaderUpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.anticipate_slide_header_up);
        mSlideHeaderDownAnimation = AnimationUtils.loadAnimation(mContext, R.anim.overshoot_slide_header_down);
        mSlideFooterUpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.overshoot_slide_footer_up);
        mSlideFooterDownAnimation = AnimationUtils.loadAnimation(mContext, R.anim.anticipate_slide_footer_down);


        mHeader =  headerView;
        mFooter =  footerView;
    }

    public SpeedyQuickReturnListViewOnScrollListener(Context context, QuickReturnType quickReturnType, ArrayList<View> headerViews, ArrayList<View> footerViews) {
        mContext = context;
        mQuickReturnType = quickReturnType;

        mHeaderViews = headerViews;
        mFooterViews = footerViews;

        mSlideHeaderUpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.anticipate_slide_header_up);
        mSlideHeaderDownAnimation = AnimationUtils.loadAnimation(mContext, R.anim.overshoot_slide_header_down);
        mSlideFooterUpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.overshoot_slide_footer_up);
        mSlideFooterDownAnimation = AnimationUtils.loadAnimation(mContext, R.anim.anticipate_slide_footer_down);
    }
    // endregion

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
      // apply extra listener first
      for (AbsListView.OnScrollListener listener : mExtraOnScrollListeners) {
          listener.onScrollStateChanged(view, scrollState);
      }
    }

    @Override
    public void onScroll(AbsListView listview, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // apply extra listener first
        for (AbsListView.OnScrollListener listener : mExtraOnScrollListeners) {
            listener.onScroll(listview, firstVisibleItem, visibleItemCount, totalItemCount);
        }
        int scrollY = QuickReturnUtils.getScrollY(listview);
        int diff = mPrevScrollY - scrollY;

//        Log.d(getClass().getSimpleName(), "onScroll() : scrollY - " + scrollY);
//        Log.d(getClass().getSimpleName(), "onScroll() : diff - " + diff);


        if(diff>0){ // scrolling up
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
                case GOOGLE_PLUS:
                    if(mHeaderViews!=null){
                        for(View view : mHeaderViews){
                            if(view.getVisibility() == View.GONE){
                                view.setVisibility(View.VISIBLE);
                                view.startAnimation(mSlideHeaderDownAnimation);
                            }
                        }
                    }

                    if(mFooterViews!=null){
                        for(View view : mFooterViews){
                            int scrollThreshold = (Integer) view.getTag(R.id.scroll_threshold_key);
                            if(diff > scrollThreshold){
                                if(view.getVisibility() == View.GONE){
                                    view.setVisibility(View.VISIBLE);
                                    view.startAnimation(mSlideFooterUpAnimation);
                                }
                            }
                        }
                    }
                    break;
            }
        } else if(diff<0){ // scrolling down
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
                case GOOGLE_PLUS:
                    if(mHeaderViews!=null){
                        for(View view : mHeaderViews){
                            if(view.getVisibility() == View.VISIBLE){
                                view.setVisibility(View.GONE);
                                view.startAnimation(mSlideHeaderUpAnimation);
                            }
                        }
                    }

                    if(mFooterViews!=null){
                        for(View view : mFooterViews){
                            int scrollThreshold = (Integer) view.getTag(R.id.scroll_threshold_key);
                            if(diff < -scrollThreshold){
                                if(view.getVisibility() == View.VISIBLE){
                                    view.setVisibility(View.GONE);
                                    view.startAnimation(mSlideFooterDownAnimation);
                                }
                            }
                        }
                    }
                    break;
            }
        }

        mPrevScrollY = scrollY;
    }

    public void registerExtraOnScrollListener(AbsListView.OnScrollListener listener) {
        mExtraOnScrollListeners.add(listener);
    }

    public void setSlideHeaderUpAnimation(Animation animation){
        mSlideHeaderUpAnimation = animation;
    }

    public void setSlideHeaderDownAnimation(Animation animation){
        mSlideHeaderDownAnimation = animation;
    }

    public void setSlideFooterUpAnimation(Animation animation){
        mSlideFooterUpAnimation = animation;
    }

    public void setSlideFooterDownAnimation(Animation animation){
        mSlideFooterDownAnimation = animation;
    }
}
