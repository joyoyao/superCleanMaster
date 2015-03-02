package com.yzy.supercleanmaster.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.BaseFragment;

import butterknife.ButterKnife;


public class NavigationDrawerFragment extends BaseFragment {

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private boolean mFromSavedInstanceState;
    private NavigationDrawerCallbacks mCallbacks;
    final int radioIds[] = {
            R.id.radio0,
            R.id.radio1,
            R.id.radio2

    };
    RadioButton radios[] = new RadioButton[radioIds.length];

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of
        // actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_navigation_drawer,
                container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillData();
    }

    private void fillData() {


        for (int i = 0; i < radioIds.length; ++i) {
            radios[i] = (RadioButton) getView().findViewById(radioIds[i]);
            radios[i].setOnClickListener(clickItem);
        }
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);


    }


    View.OnClickListener clickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < radios.length; ++i) {
                if (v.equals(radios[i])) {
                    selectItem(i);
                } else {
                    radios[i].setChecked(false);
                }
            }
        }
    };


    private void selectItem(int position) {
//        if (mDrawerLayout != null) {
//            mDrawerLayout.closeDrawer(mFragmentContainerView);
//        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }


    private ActionBar getActionBar() {
        return getActivity().getActionBar();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    "Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(int position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
