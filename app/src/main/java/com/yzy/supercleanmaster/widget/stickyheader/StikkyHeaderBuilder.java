package com.yzy.supercleanmaster.widget.stickyheader;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yzy.supercleanmaster.widget.stickyheader.animator.HeaderStikkyAnimator;

public abstract class StikkyHeaderBuilder {

	protected final Context mContext;

	protected View mHeader;
	protected int mMinHeight;
	protected HeaderAnimator mAnimator;

	protected StikkyHeaderBuilder(final Context context) {
		mContext = context;
		mMinHeight = 0;
	}

	public static ListViewBuilder stickTo(final ListView listView) {
		return new ListViewBuilder(listView);
	}

	public StikkyHeaderBuilder setHeader(@IdRes final int idHeader,
			final ViewGroup view) {
		mHeader = view.findViewById(idHeader);
		return this;
	}

	public StikkyHeaderBuilder setHeader(final View header) {
		mHeader = header;
		return this;
	}

	public StikkyHeaderBuilder minHeightHeaderRes(
			@DimenRes final int resDimension) {
		mMinHeight = mContext.getResources()
				.getDimensionPixelSize(resDimension);
		return this;
	}

	public StikkyHeaderBuilder minHeightHeaderPixel(final int minHeight) {
		mMinHeight = minHeight;
		return this;
	}

	public StikkyHeaderBuilder animator(final HeaderAnimator animator) {
		mAnimator = animator;
		return this;
	}

	public abstract StikkyHeader build();

	public static class ListViewBuilder extends StikkyHeaderBuilder {

		private final ListView mListView;

		protected ListViewBuilder(final ListView listView) {
			super(listView.getContext());
			mListView = listView;
		}

		@Override
		public StikkyHeaderListView build() {

			// if the animator has not been set, the default one is used
			if (mAnimator == null) {
				mAnimator = new HeaderStikkyAnimator();
			}

			return new StikkyHeaderListView(mContext, mListView, mHeader,
					mMinHeight, mAnimator);
		}
	}

}
