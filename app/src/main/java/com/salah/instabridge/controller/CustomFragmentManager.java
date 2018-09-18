package com.salah.instabridge.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.instabridge.wifiprovider.WiFi;
import com.salah.instabridge.fragments.BaseFragment;
import com.salah.instabridge.activity.MainActivity;
import com.salah.instabridge.R;
import com.salah.instabridge.fragments.DetailsFragment;

/**
 * Created by salah on 9/15/18.
 */

public class CustomFragmentManager {

    public static final String PASSED_OBJECT = "passed";

    private BaseFragment mCurrentFragment;
    private MainActivity mainActivity;
    private static CustomFragmentManager customFragmentManager;

    public void initializeFragmentManager(BaseFragment mCurrentFragment, MainActivity mainActivity) {
        this.mCurrentFragment = mCurrentFragment;
        this.mainActivity = mainActivity;
    }

    public static CustomFragmentManager getInstance() {
        if (customFragmentManager == null)
            customFragmentManager = new CustomFragmentManager();
        return customFragmentManager;
    }

    public void updateFragment(BaseFragment mCurrentFragment) {
        if (mCurrentFragment != null) {
            this.mCurrentFragment = mCurrentFragment;
            FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_frame, mCurrentFragment).addToBackStack(null).commit();
        }
    }

    public void navigateToDetails(WiFi wiFi) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PASSED_OBJECT, wiFi);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        updateFragment(detailsFragment);
    }
}
