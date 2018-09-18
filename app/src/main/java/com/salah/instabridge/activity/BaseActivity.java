package com.salah.instabridge.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by salah on 9/15/18.
 */

public class BaseActivity extends FragmentActivity {

    private Dialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
    }


    public void showProgressDialog() {
        if (!mProgressDialog.isShowing()) {
            if (mProgressDialog != null)
                mProgressDialog.show();
        }
    }

    public void hideProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
