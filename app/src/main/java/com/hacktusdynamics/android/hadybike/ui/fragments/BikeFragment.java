package com.hacktusdynamics.android.hadybike.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hacktusdynamics.android.hadybike.R;

public class BikeFragment extends Fragment {
    private static final String TAG = BikeFragment.class.getSimpleName();

    public BikeFragment() {
        // Required empty public constructor
    }

    public static BikeFragment newInstance() {
        BikeFragment fragment = new BikeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bike, container, false);
        return rootView;
    }

}
