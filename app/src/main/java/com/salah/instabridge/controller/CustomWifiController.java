package com.salah.instabridge.controller;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.instabridge.wifiprovider.WiFi;
import com.instabridge.wifiprovider.WifiProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by salah on 9/15/18.
 */

public class CustomWifiController extends BroadcastReceiver {

    private WifiManager wifiManager;
    private ArrayList<ScanResult> scanResults;

    private WifiProvider wifiProvider;

    public CustomWifiController(Activity activity, WifiProvider wifiProvider) {
        this.wifiProvider = wifiProvider;
        wifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        askForPermission(activity);
        activity.registerReceiver(this, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    public void startScan() {
        wifiManager.startScan();
    }

    private List<WiFi> createWifiList(ArrayList<ScanResult> scanResults) {
        List<WiFi> wiFis = new ArrayList<>();
        for (int i = 0; i < scanResults.size(); i++) {

            boolean hasPassword = scanResults.get(i).capabilities.contains("WPA");

            WiFi wiFi = new WiFi(scanResults.get(i).SSID, scanResults.get(i).level, hasPassword);

            wiFi.setFrequency(scanResults.get(i).frequency);
            wiFi.setBssid(scanResults.get(i).BSSID);

            wiFis.add(wiFi);
        }

        return wiFis;
    }

    private void askForPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
            scanResults = (ArrayList<ScanResult>) wifiManager.getScanResults();
            if (wifiProvider != null) {
                wifiProvider.onCloseByUpdate(createWifiList(scanResults));
            }
        }
    }
}
