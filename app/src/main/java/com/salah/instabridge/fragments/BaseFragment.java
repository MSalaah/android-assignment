package com.salah.instabridge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by salah on 9/15/18.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(getContentLayout(), container, false);
        initializeUIComponents(fragmentView,savedInstanceState);
        initializeUIComponentsData();
        initializeUIComponentsAction();
        return fragmentView;
    }

    protected abstract void initializeUIComponents(View view, @Nullable Bundle savedInstanceState);

    protected abstract void initializeUIComponentsData();

    protected abstract void initializeUIComponentsAction();

    protected abstract int getContentLayout();

}

