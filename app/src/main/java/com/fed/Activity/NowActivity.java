package com.fed.Activity;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.content.Intent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import com.fedclient.R;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fed.CircleProgress;
import java.util.Random;


import android.widget.TextView;

import java.util.List;


public class NowActivity<TimeLineView, DiscreteSeekBar> extends AppCompatActivity implements View.OnClickListener{
    private RadioButton RB_home;
    private RadioButton RB_history;
    private RadioButton RB_shezhi;
    private RadioButton RB_mine;

    private ProgressBar progesss;
    private TextView progesssValue;
    private LinearLayout full;
    private Object ToastUtil;

    private final static int[] COLORS = new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    private Button mBtnResetAll;
    private CircleProgress mCircleProgress1, mCircleProgress2, mCircleProgress3;
    private Random mRandom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_now);

        mBtnResetAll = (Button) findViewById(R.id.btn_reset_all);
        mCircleProgress2 = (CircleProgress) findViewById(R.id.circle_progress_bar2);
        mCircleProgress3 = (CircleProgress) findViewById(R.id.circle_progress_bar3);

        mBtnResetAll.setOnClickListener(this);
        mCircleProgress2.setOnClickListener(this);
        mCircleProgress3.setOnClickListener(this);

        mRandom = new Random();
        init_view();

        RB_home = (RadioButton) findViewById (R.id.RB_home);
        RB_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NowActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_history = (RadioButton) findViewById (R.id.RB_history);
        RB_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NowActivity.this,HistoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_shezhi = (RadioButton) findViewById (R.id.RB_shezhi);
        RB_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NowActivity.this,ShezhiActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_mine = (RadioButton) findViewById (R.id.RB_mine);
        RB_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NowActivity.this,MineActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void init_view() {
        mCircleProgress2.setValue(mRandom.nextFloat() * mCircleProgress2.getMaxValue());
        mCircleProgress3.setGradientColors(COLORS);
        mCircleProgress3.setValue(mRandom.nextFloat() * mCircleProgress3.getMaxValue());
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset_all:
                mCircleProgress2.reset();
                mCircleProgress3.reset();
                break;
            case R.id.circle_progress_bar2:
                mCircleProgress2.setValue(mRandom.nextFloat() * mCircleProgress2.getMaxValue());
                break;
            case R.id.circle_progress_bar3:
                //在代码中动态改变渐变色，可能会导致颜色跳跃
                mCircleProgress3.setGradientColors(COLORS);
                mCircleProgress3.setValue(mRandom.nextFloat() * mCircleProgress3.getMaxValue());
                break;
        }
    }
}