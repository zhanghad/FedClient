package com.fedclient.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.fedclient.R;

public class HistoryDetailActivity extends AppCompatActivity {
    private static final String TAG = "TaskDetailActivity";
    private TextView tv_hd_id;
    private TextView tv_hd_status;
    private TextView tv_hd_iternum;
    private TextView tv_hd_eval;
    private TextView tv_hd_starttime;
    private TextView tv_hd_endtime;
    private RadioButton rb_hd_back;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historydetail);
        initComponents();
        setListeners();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();

    }

    /**
     * 初始化组件及数据
     */
    private void initComponents(){
        tv_hd_id=(TextView)findViewById(R.id.tv_hd_id);
        tv_hd_status=(TextView)findViewById(R.id.tv_hd_status);
        tv_hd_iternum=(TextView)findViewById(R.id.tv_hd_iternum);
        tv_hd_eval=(TextView)findViewById(R.id.tv_hd_eval);
        tv_hd_starttime=(TextView)findViewById(R.id.tv_hd_starttime);
        tv_hd_endtime=(TextView)findViewById(R.id.tv_hd_endtime);
        rb_hd_back=(RadioButton) findViewById(R.id.rb_hd_back);
    }


    /**
     * 设置监听器
     */
    private void setListeners(){
        rb_hd_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HistoryDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
