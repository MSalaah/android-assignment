package com.salah.instabridge.fragments;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.instabridge.wifiprovider.WiFi;
import com.instabridge.wifiprovider.WifiProvider;
import com.salah.instabridge.R;
import com.salah.instabridge.activity.BaseActivity;
import com.salah.instabridge.activity.MainActivity;
import com.salah.instabridge.adapters.WiFiAdapter;
import com.salah.instabridge.controller.CustomFragmentManager;
import com.salah.instabridge.controller.CustomWifiController;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by salah on 9/18/18.
 */

public class RealListFragment extends BaseFragment implements WifiProvider {

    private CustomWifiController customWifiController;
    private ListView listView;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_real_list;
    }

    @Override
    protected void initializeUIComponents(View view, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setCurrentFragment(this);
        ((MainActivity) getActivity()).getToolbar().setTitle(getString(R.string.real_list_fragment));
        ((MainActivity) getActivity()).getToolbar().getMenu().clear();
        ((MainActivity) getActivity()).getToolbar().setNavigationIcon(R.drawable.back);

        listView = view.findViewById(R.id.lv_real_wifi);

        listView.setEmptyView(view.findViewById(R.id.empty));
    }

    @Override
    protected void initializeUIComponentsData() {
        initWifiController();
    }

    @Override
    protected void initializeUIComponentsAction() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                WiFi wiFi = (WiFi) adapterView.getItemAtPosition(position);
                CustomFragmentManager.getInstance().navigateToDetails(wiFi);
            }
        });
    }

    private void initWifiController() {
        customWifiController = new CustomWifiController(getActivity(), this);
    }


    @Override
    public void onCloseByUpdate(@NonNull List<WiFi> items) {
        if (getContext() != null) {
            ((BaseActivity) getActivity()).hideProgressDialog();
            if (items.size() > 0) {
                WiFiAdapter wiFiAdapter = new WiFiAdapter(getContext(), (ArrayList<WiFi>) items, R.layout.item_wifi);
                listView.setAdapter(wiFiAdapter);
            } else {
                Toast.makeText(getContext(), getString(R.string.location_service), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFarAwayUpdate(List<WiFi> items) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    customWifiController.startScan();
                    ((BaseActivity) getActivity()).showProgressDialog();
                    break;
                }
            }
        }
    }
}
