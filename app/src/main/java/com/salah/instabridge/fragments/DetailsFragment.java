package com.salah.instabridge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.instabridge.wifiprovider.WiFi;
import com.salah.instabridge.R;
import com.salah.instabridge.activity.MainActivity;
import com.salah.instabridge.controller.CustomFragmentManager;

/**
 * Created by salah on 9/15/18.
 */

public class DetailsFragment extends BaseFragment {

    private TextView tvName;
    private TextView tvFrequencey;
    private TextView tvBssid;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_details;
    }

    @Override
    protected void initializeUIComponents(View view, @Nullable Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setCurrentFragment(this);
        ((MainActivity) getActivity()).getToolbar().setTitle(getString(R.string.details_fragment));


        ((MainActivity) getActivity()).getToolbar().getMenu().clear();
        ((MainActivity) getActivity()).getToolbar().setNavigationIcon(R.drawable.back);

        tvName = view.findViewById(R.id.tv_name);
        tvFrequencey = view.findViewById(R.id.tv_frequency);
        tvBssid = view.findViewById(R.id.tv_bssid);

    }

    @Override
    protected void initializeUIComponentsData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            WiFi wiFi = (WiFi) bundle.getSerializable(CustomFragmentManager.PASSED_OBJECT);
            if (wiFi != null) {
                setData(wiFi);
            }
        }
    }

    private void setData(WiFi wiFi) {
        tvName.setText(wiFi.getSsid());

        if (wiFi.getBssid() != null)
            tvBssid.setText(wiFi.getBssid());

        if (wiFi.getFrequency() != null)
            tvFrequencey.setText(String.valueOf(wiFi.getFrequency()));
    }

    @Override
    protected void initializeUIComponentsAction() {

    }
}
