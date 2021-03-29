package com.fedclient.manager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;

import com.fedclient.domain.ClientDevice;
import com.fedclient.receiver.BatteryReceiver;
import com.fedclient.ui.activity.LoginActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

public class DeviceManager {
    private static final String TAG = "DeviceManager";
    private static ClientDevice myDevice;
    private static Context mcontext;



    public static void setMcontext(Context context){
        mcontext=context;
    }

    public static void setPowered(boolean flag){

        
        myDevice.setPowered(flag);
    }

    public static void setBatteryCapacity(Double p){
        myDevice.setBatteryCapacity(p);
    }



    public static ClientDevice getDeviceInstance() throws IOException {
        if(myDevice==null){
            myDevice=new ClientDevice();
            myDevice.setDeviceCode(deviceCode(mcontext));
            myDevice.setClientId(ClientManager.getCurrentClient().getClientId());
            myDevice.setAndroidVersion(androidVersion());
            myDevice.setBatteryCapacity(batteryCapacity());
            myDevice.setOs(os());
            myDevice.setPowered(powered());
            myDevice.setProc(proc());
            myDevice.setStorage(storage());
            myDevice.setRamCapacity(ramCapacity());
            myDevice.setDeviceType(deviceType());
        }
        return myDevice;
    }

    public static void deviceIsPowered(boolean flag){
        if(flag){
            myDevice.setPowered("1");
        }else {
            myDevice.setPowered("0");
        }
    }


    private static String deviceCode(Context context){
        String uniqueID;
        SharedPreferences deviceID = context.getSharedPreferences("device",MODE_PRIVATE);
        String temp=deviceID.getString("deviceID",null);
        if(temp!=null){
            uniqueID=temp;
        }else {
            SharedPreferences.Editor editor = deviceID.edit();
            uniqueID = UUID.randomUUID().toString();
            editor.putString("deviceID", uniqueID);
            editor.apply();
        }
        return uniqueID;
    }

    private static String deviceType(){
        return Build.BRAND+" "+Build.MODEL;
    }

    private static String proc() throws IOException {
        String s1="/proc/cpuinfo";
        FileReader fileReader=new FileReader(s1);
        BufferedReader localBufferedReader = new BufferedReader(fileReader, 8192);
        String[] str2 = localBufferedReader.readLine().split(": ");
        return str2[1];
    }

    private static Double batteryCapacity(){
        Object mPowerProfile;
        double mBatteryCapacity = 0;
//        String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";
//
//        try {
//            mPowerProfile = Class.forName(POWER_PROFILE_CLASS)
//                    .getConstructor(Context.class)
//                    .newInstance(context);
//
//
//            mBatteryCapacity = (double) Class.forName(POWER_PROFILE_CLASS)
//                    .getMethod("getBatteryCapacity")
//                    .invoke(mPowerProfile);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return mBatteryCapacity;
    }

    private static Double ramCapacity() throws IOException {
        String s1="/proc/meminfo";
        String ram="0";
        FileReader fileReader=new FileReader(s1);
        BufferedReader localBufferedReader = new BufferedReader(fileReader, 8192);
        String[] str2 = localBufferedReader.readLine().split(" ");

        for(int i=str2.length-1;i>0;i--){
            if(!str2[i].equals("MemTotal:") && !(str2[i]==null) && !str2[i].equals("kB")){
                ram=str2[i];
                break;
            }
        }
        return Double.parseDouble(ram)/(1024*1024);
    }

    private static String androidVersion(){
        return Build.VERSION.RELEASE;
    }

    private static String os(){
        return Build.FINGERPRINT;
    }

    private static Double storage(){
        final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        Long totalCounts = statFs.getBlockCountLong();
        Long size = statFs.getBlockSizeLong();
        return (totalCounts.doubleValue() *size.doubleValue())/(1024*1024*1024);
    }

    private static String powered(){
        return "0";
    }
}
