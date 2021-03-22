package com.fedclient.ui.activity;

import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.widget.ImageView;

import com.fedclient.R;
import androidx.appcompat.app.AppCompatActivity;

public class TrainStatusActivity extends AppCompatActivity{
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_trainstatus);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}