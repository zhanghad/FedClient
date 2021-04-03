package com.fedclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.fedclient.R;

public class TrainstatusFragment extends Fragment {
    private TextView tv_trainstatus;
    private TextView tv_totalstatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traintatus,container,false);
        initdata();
        return view;
    }

    void initdata(){

    }


}
