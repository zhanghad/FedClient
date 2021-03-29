package com.fedclient.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.fedclient.receiver.BatteryReceiver;

public class BatteryService extends Service {

    IntentFilter filter;
    BatteryReceiver mBatteryReceiver=new BatteryReceiver();

    @Override
    public void onCreate() {
        super.onCreate();
        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatteryReceiver, filter);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mBatteryReceiver);
        super.onDestroy();
    }
}
