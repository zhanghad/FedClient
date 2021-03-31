package com.fedclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fedclient.R;
import com.fedclient.ui.activity.ConfigActivity;

public class Config_Fragment extends Fragment {
    Switch s;
    public EditText et;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config,container,false);
        initComponents(view);
        setListeners();
        return view;
    }


    /**
     * 初始化组件及数据
     */
    private void initComponents(View view){

        s = view.findViewById(R.id.kaiguan);
        TextView textView1 = (TextView) view.findViewById(R.id.RELEASE);
        textView1.setText(android.os.Build.VERSION.RELEASE );
        TextView product = (TextView) view.findViewById(R.id.product);
        product.setText(android.os.Build.PRODUCT);
        TextView cpu_abi = (TextView) view.findViewById(R.id.cpu_abi);
        cpu_abi.setText(android.os.Build.CPU_ABI);
        TextView tags = (TextView) view.findViewById(R.id.tags );
        tags.setText(android.os.Build.TAGS );
        TextView sdk = (TextView) view.findViewById(R.id.sdk );
        sdk.setText(android.os.Build.VERSION.SDK);
        TextView device = (TextView) view.findViewById(R.id.device);
        device.setText(android.os.Build.DEVICE);
        TextView maker = (TextView) view.findViewById(R.id.maker);
        maker.setText(android.os.Build.MANUFACTURER);
        TextView hardware = (TextView) view.findViewById(R.id.hardware);
        hardware.setText(android.os.Build.MANUFACTURER);
    }

    /**
     * 设置监听器
     */
    private void setListeners(){
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (s.isChecked()){
                    Toast.makeText(getActivity(),"开启",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(),"关闭",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}
