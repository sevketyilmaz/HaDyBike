package com.hacktusdynamics.android.hadybike.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hacktusdynamics.android.hadybike.R;

public class TimelineFragment extends Fragment {
    private static final String TAG = TimelineFragment.class.getSimpleName();

    public TimelineFragment() {
        // Required empty public constructor
    }

    public static TimelineFragment newInstance(){
        TimelineFragment fragment = new TimelineFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
        return rootView;
    }

}
