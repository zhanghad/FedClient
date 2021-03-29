package com.fedclient.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.fedclient.manager.DeviceManager;

public class BatteryReceiver extends BroadcastReceiver {
    public int current=-1;
    public int total=-1;
    public int percent=-1;
    public boolean isPowered=false;

    @Override
    public void onReceive(Context context, Intent intent) {
        current = intent.getExtras().getInt("level");// 获得当前电量
        total = intent.getExtras().getInt("scale");// 获得总电量
        percent = current * 100 / total;

        int temp=intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
        if(temp==BatteryManager.BATTERY_STATUS_CHARGING){
            isPowered=true;
        }else {
            isPowered=false;
        }

    }
}
