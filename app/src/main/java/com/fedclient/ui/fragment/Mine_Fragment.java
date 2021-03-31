package com.fedclient.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fedclient.R;
import com.fedclient.ui.activity.ClientInfoActivity;

public class Mine_Fragment extends Fragment {
    private Button btn_takephoto;//拍照
    private Button btn_fromalbum;//相册

    private Button btn_Edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        initView(view);
        initData();
        return view;
    }

    void initView(View view) {
        btn_Edit = view.findViewById(R.id.btn_edit);
    }

    void initData() {
        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ClientInfoActivity.class);//在Fragment中实现开启新的Activity
                startActivity(intent);
            }
        });
    }
}
