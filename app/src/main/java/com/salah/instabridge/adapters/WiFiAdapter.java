package com.salah.instabridge.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.instabridge.wifiprovider.WiFi;
import com.salah.instabridge.R;

import java.util.ArrayList;

/**
 * Created by salah on 9/18/18.
 */

public class WiFiAdapter extends ArrayAdapter<WiFi> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<WiFi> wiFiArrayList;

    public WiFiAdapter(@NonNull Context context, ArrayList<WiFi> wiFis, int layoutResourceId) {
        super(context, layoutResourceId, wiFis);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.wiFiArrayList = wiFis;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        WiFiHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = initializeRowComponent(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (WiFiHolder) convertView.getTag();
        }
        WiFi wiFi = wiFiArrayList.get(position);
        setData(holder, wiFi);

        return convertView;
    }

    private WiFiHolder initializeRowComponent(WiFiHolder holder, View row) {
        holder = new WiFiHolder();
        holder.ivLevel = row.findViewById(R.id.iv_level);
        holder.tvSsid = row.findViewById(R.id.tv_ssid);
        holder.ivLock = row.findViewById(R.id.iv_lock);
        return holder;
    }

    private void setData(final WiFiHolder holder, final WiFi wiFi) {
        holder.tvSsid.setText(wiFi.getSsid());

        if (wiFi.getHasPassword())
            holder.ivLock.setVisibility(View.VISIBLE);
        else
            holder.ivLock.setVisibility(View.GONE);

        if (wiFi.getRssi() < -70)
            //Weak Wifi Strength
            holder.ivLevel.setBackgroundResource(R.drawable.baseline_signal_wifi_1_bar_black_24dp);
        else if (wiFi.getRssi() >= -70 && wiFi.getRssi() <= -60)
            //fair Wifi Strength
            holder.ivLevel.setBackgroundResource(R.drawable.baseline_signal_wifi_2_bar_black_24dp);
        else if (wiFi.getRssi() >= -60 && wiFi.getRssi() <= -50)
            //Good Wifi Strength
            holder.ivLevel.setBackgroundResource(R.drawable.baseline_signal_wifi_3_bar_black_24dp);
        else if (wiFi.getRssi() > -50)
            //Excellent Wifi Strength
            holder.ivLevel.setBackgroundResource(R.drawable.baseline_signal_wifi_4_bar_black_24dp);
    }

    private class WiFiHolder {
        ImageView ivLevel;
        TextView tvSsid;
        ImageView ivLock;

    }
}
