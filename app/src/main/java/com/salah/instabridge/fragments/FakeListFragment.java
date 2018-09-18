package com.salah.instabridge.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.instabridge.wifiprovider.FakeWiFiProvider;
import com.instabridge.wifiprovider.WiFi;
import com.instabridge.wifiprovider.WifiProvider;
import com.salah.instabridge.R;
import com.salah.instabridge.controller.CustomFragmentManager;
import com.salah.instabridge.activity.MainActivity;
import com.salah.instabridge.adapters.WiFiAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by salah on 9/15/18.
 */

public class FakeListFragment extends BaseFragment implements WifiProvider {

    private ListView lvCloseBy;
    private ListView lvFarAway;

    private Button btnShowMore;


    private FakeWiFiProvider fakeWiFiProvider;

    //Adapters
    private WiFiAdapter closeByAdapter;
    private WiFiAdapter furtherAwayAdapter;

    //ArrayLists
    private ArrayList<WiFi> closeWiFiArrayList;
    private ArrayList<WiFi> farWiFiArrayList;

    private ArrayList<WiFi> itemsToAppend;

    private static final int MAX_VISIBLE_ITEMS = 3;


    @Override
    protected int getContentLayout() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initializeUIComponents(View view, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setCurrentFragment(this);
        ((MainActivity) getActivity()).getToolbar().setTitle(getString(R.string.fake_list_fragment));

        ((MainActivity) getActivity()).getToolbar().getMenu().clear();
        ((MainActivity) getActivity()).getToolbar().inflateMenu(R.menu.menu_main);
        ((MainActivity) getActivity()).getToolbar().setNavigationIcon(null);


        lvCloseBy = view.findViewById(R.id.lv_close_by);
        lvFarAway = view.findViewById(R.id.lv_far_away);
        btnShowMore = view.findViewById(R.id.btn_show_more);

        addHeader(lvCloseBy, getString(R.string.close_by));
        addHeader(lvFarAway, getString(R.string.further_away));
    }

    @Override
    protected void initializeUIComponentsData() {
        initFakeWifi();
    }

    @Override
    protected void initializeUIComponentsAction() {
        btnShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFarWiFis(itemsToAppend);
                furtherAwayAdapter.notifyDataSetChanged();
            }
        });

        lvCloseBy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                navigateAndStop(adapterView, position);
            }
        });

        lvFarAway.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                navigateAndStop(adapterView, position);
            }
        });
    }

    private void navigateAndStop(AdapterView adapterView, int position) {
        WiFi wiFi = (WiFi) adapterView.getItemAtPosition(position);
        stopFakeWifi();
        CustomFragmentManager.getInstance().navigateToDetails(wiFi);
    }

    public void stopFakeWifi() {
        fakeWiFiProvider.stop();
    }

    private void addHeader(ListView listView, String title) {
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.item_header, listView, false);
        TextView tvHeader = header.findViewById(R.id.tv_header);
        tvHeader.setText(title);
        listView.addHeaderView(header, null, false);
    }

    private void initFakeWifi() {
        fakeWiFiProvider = new FakeWiFiProvider(this);
        fakeWiFiProvider.start();
    }

    private void createCloseBy(ArrayList<WiFi> wiFis) {
        // 1. initialize or clear the WiFis array
        if (closeWiFiArrayList == null)
            closeWiFiArrayList = new ArrayList<>();
        else
            closeWiFiArrayList.clear();
        // 2. sort all the WiFis according to RSSI
        sortItems(wiFis);
        // 3. We need only 3 WiFis, So we will remove any extra WiFis from the list
        for (int i = 0; i < MAX_VISIBLE_ITEMS && i < wiFis.size(); i++) {
            closeWiFiArrayList.add(wiFis.get(i));
        }
    }

    private void createFarAway(ArrayList<WiFi> wiFis) {
        // 1. initialize or clear the WiFis array
        if (farWiFiArrayList == null)
            farWiFiArrayList = new ArrayList<>();
        else
            farWiFiArrayList.clear();
        // 2. sort all the WiFis according to RSSI
        sortItems(wiFis);
        // 3.pass data to showFarWiFis Function
        showFarWiFis(wiFis);
    }

    private void showFarWiFis(ArrayList<WiFi> wiFis) {
        // only show 3 items and append the rest to the items to appended Items list
        itemsToAppend = new ArrayList<>();
        for (int i = 0; i < wiFis.size(); i++) {
            if (i < MAX_VISIBLE_ITEMS) {
                farWiFiArrayList.add(wiFis.get(i));
            } else {
                itemsToAppend.add(wiFis.get(i));
            }
        }
        // only show button when there is more wifi to add
        if (itemsToAppend.size() > 0)
            btnShowMore.setVisibility(View.VISIBLE);
        else
            btnShowMore.setVisibility(View.GONE);
    }


    private void sortItems(ArrayList<WiFi> wiFis) {
        Collections.sort(wiFis, new Comparator<WiFi>() {
            public int compare(WiFi o1, WiFi o2) {
                return o2.getRssi() - (o1.getRssi());
            }
        });
    }

    @Override
    public void onCloseByUpdate(@NonNull List<WiFi> items) {
        createCloseBy((ArrayList<WiFi>) items);
        if (closeByAdapter == null)
            closeByAdapter = new WiFiAdapter(getContext(), closeWiFiArrayList, R.layout.item_wifi);

        lvCloseBy.setAdapter(closeByAdapter);
        closeByAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFarAwayUpdate(@NonNull List<WiFi> items) {
        createFarAway((ArrayList<WiFi>) items);
        if (furtherAwayAdapter == null)
            furtherAwayAdapter = new WiFiAdapter(getContext(), farWiFiArrayList, R.layout.item_wifi);

        lvFarAway.setAdapter(furtherAwayAdapter);
        furtherAwayAdapter.notifyDataSetChanged();
    }
}

