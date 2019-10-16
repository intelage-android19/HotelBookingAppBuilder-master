package com.efunhub.hotelbooking.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.efunhub.hotelbooking.utility.SessionManager;


/**
 * Created by Admin on 14-07-2018.
 */

public class InstallReferrerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String referrer = intent.getStringExtra("referrer");

        if (!referrer.isEmpty() || !referrer.equalsIgnoreCase("")) {
            SessionManager sessionManager = new SessionManager(context);
            sessionManager.storeReferrerIdInPref(referrer);
        }
    }
}
