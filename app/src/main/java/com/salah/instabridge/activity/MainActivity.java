package com.salah.instabridge.activity;


import android.content.pm.PackageManager;
import android.graphics.Color;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.salah.instabridge.controller.CustomFragmentManager;
import com.salah.instabridge.fragments.BaseFragment;
import com.salah.instabridge.R;
import com.salah.instabridge.fragments.FakeListFragment;
import com.salah.instabridge.fragments.RealListFragment;


/**
 * Created by salah on 9/15/18.
 */

public class MainActivity extends BaseActivity {

    private BaseFragment currentFragment;
    private Toolbar toolbar;

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomFragmentManager.getInstance().initializeFragmentManager(currentFragment, this);
        initializeToolBar();
        CustomFragmentManager.getInstance().updateFragment(new FakeListFragment());
    }

    private void initializeToolBar() {
        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.show_real_wifi:
                        ((FakeListFragment) currentFragment).stopFakeWifi();
                        CustomFragmentManager.getInstance().updateFragment(new RealListFragment());
                        return false;
                }
                return false;
            }
        });
    }


    public void setCurrentFragment(BaseFragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void onBackPressed() {
        if (!(currentFragment instanceof FakeListFragment))
            super.onBackPressed();
        else
            finish();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    currentFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    break;
                }
            }
        }
    }
}
