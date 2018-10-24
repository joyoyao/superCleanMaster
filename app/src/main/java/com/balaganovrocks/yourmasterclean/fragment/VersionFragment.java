package com.balaganovrocks.yourmasterclean.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balaganovrocks.yourmasterclean.base.FragmentContainerActivity;
import com.balaganovrocks.yourmasterclean.R;
import com.balaganovrocks.yourmasterclean.base.FragmentContainerActivity;


public class VersionFragment extends Fragment {


    public static void launch(Activity from) {
        FragmentContainerActivity.launch(from, VersionFragment.class, null);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_version, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().getActionBar().setDisplayShowHomeEnabled(false);
        getActivity().getActionBar().setTitle(
                "Информация о версии");

    }


}
