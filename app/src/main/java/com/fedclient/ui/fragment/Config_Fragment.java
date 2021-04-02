package com.fedclient.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.fedclient.R;
import com.fedclient.domain.ClientDevice;
import com.fedclient.manager.DeviceManager;
import java.io.IOException;



public class Config_Fragment extends Fragment {
    DeviceManager deviceManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, container, false);

        try {
            initComponents(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }


    /**
     * 初始化组件及数据
     */
    private void initComponents(View view) throws IOException {
        ClientDevice clientDevice=DeviceManager.getDeviceInstance();

        TextView tv_deviceCode = (TextView) view.findViewById(R.id.deviceCode);
        String DeviceCode = String.valueOf(clientDevice.getDeviceCode());
        tv_deviceCode.setText(DeviceCode);

        TextView tv_clientId = (TextView) view.findViewById(R.id.clientId);
        String clientid = String.valueOf(clientDevice.getClientId());
        tv_clientId.setText(clientid);

        TextView tv_androidVersion = (TextView) view.findViewById(R.id.androidVersion);
        tv_androidVersion.setText(clientDevice.getAndroidVersion());

        TextView tv_os = (TextView) view.findViewById(R.id.Os);
        tv_os.setText(clientDevice.getOs());

        TextView tv_powered = (TextView) view.findViewById(R.id.powered);
        tv_powered.setText(clientDevice.getPowered());

        TextView tv_batteryCapacity = (TextView) view.findViewById(R.id.batteryCapacity);
        String batterycapacity = String.valueOf(clientDevice.getBatteryCapacity());
        tv_batteryCapacity.setText(batterycapacity);

        TextView tv_proc = (TextView) view.findViewById(R.id.proc);
        tv_proc.setText(clientDevice.getProc());

        TextView tv_storage = (TextView) view.findViewById(R.id.storage);
        String Storage = String.valueOf(clientDevice.getStorage());
        tv_storage.setText(Storage);

        TextView tv_ramCapacity = (TextView) view.findViewById(R.id.ramCapacity);
        String ramCapacity = String.valueOf(clientDevice.getRamCapacity());
        tv_ramCapacity.setText(ramCapacity);

        TextView tv_deviceType = (TextView) view.findViewById(R.id.deviceType);
        tv_deviceType.setText(clientDevice.getDeviceType());

    }

}
