package com.fedclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.fedclient.R;
import com.fedclient.ui.fragment.ConfigFragment;
import com.fedclient.ui.fragment.HistoryFragment;
import com.fedclient.ui.fragment.HomeFragment;
import com.fedclient.ui.fragment.ClientInfoFragment;
import com.fedclient.ui.fragment.TrainstatusFragment;

/**
 * 主页,用于展示已发布任务的信息
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private static final String TAG = "HomeActivity";
    private ImageView titleLeftImv;
    private TextView titleTv;
    private HomeFragment fg_home;
    private HistoryFragment fg_history;
    private TrainstatusFragment fg_trainstatus;
    private ConfigFragment fg_config;
    private ClientInfoFragment fg_mine;


    private RelativeLayout home_layout;
    private RelativeLayout history_layout;
    private RelativeLayout trainstatus_layout;
    private RelativeLayout config_layout;
    private RelativeLayout mine_layout;
    private ImageView im_home;
    private ImageView im_history;
    private ImageView im_trainstatus;
    private ImageView im_config;
    private ImageView im_mine;
    private TextView tx_home;
    private TextView tx_history;
    private TextView tx_trainstatus;
    private TextView tx_config;
    private TextView tx_mine;

    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int dark = 0xff000000;

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView(); // 初始化界面控件
        setChioceItem(0); // 初始化页面加载时显示第一个选项卡

    }


    private void initView() {
        // 初始化页面标题栏

/*        titleLeftImv = (ImageView) findViewById(R.id.title_imv);
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });*/

        titleTv = (TextView) findViewById(R.id.title_text_tv);
        titleTv.setText("首 页");
        // 初始化底部导航栏的控件
        im_home = (ImageView) findViewById(R.id.im_home);
        im_history = (ImageView) findViewById(R.id.im_history);
        im_trainstatus = (ImageView) findViewById(R.id.im_trainstatus);
        im_config = (ImageView) findViewById(R.id.im_config);
        im_mine = (ImageView) findViewById(R.id.im_mine);

        tx_home = (TextView) findViewById(R.id.tx_home);
        tx_history = (TextView) findViewById(R.id.tx_history);
        tx_trainstatus = (TextView) findViewById(R.id.tx_trainstatus);
        tx_config = (TextView) findViewById(R.id.tx_config);
        tx_mine = (TextView) findViewById(R.id.tx_mine);

        home_layout = (RelativeLayout) findViewById(R.id.home);
        history_layout = (RelativeLayout) findViewById(R.id.home_history);
        trainstatus_layout = (RelativeLayout) findViewById(R.id.home_trainstatus);
        config_layout = (RelativeLayout) findViewById(R.id.home_config);
        mine_layout = (RelativeLayout) findViewById(R.id.home_mine);

        home_layout.setOnClickListener(MainActivity.this);
        history_layout.setOnClickListener(MainActivity.this);
        trainstatus_layout.setOnClickListener(MainActivity.this);
        config_layout.setOnClickListener(MainActivity.this);
        mine_layout.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                setChioceItem(0);
                break;
            case R.id.home_history:
                setChioceItem(1);
                break;
            case R.id.home_trainstatus:
                setChioceItem(2);
                break;
            case R.id.home_config:
                setChioceItem(3);
                break;
            case R.id.home_mine:
                setChioceItem(4);
                break;
            default:
                break;
        }
    }

    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1, 2, 3, 4
     */
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChoice(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
                tx_home.setTextColor(dark);
                home_layout.setBackgroundColor(gray);
                titleTv.setText("主 页");
                if (fg_home == null) {
                    fg_home = new HomeFragment();
                    fragmentTransaction.add(R.id.content, fg_home);
                } else {
                    fragmentTransaction.show(fg_home);
                }
                break;
            case 1:
                tx_history.setTextColor(dark);
                history_layout.setBackgroundColor(gray);
                titleTv.setText("历史记录");
                if (fg_history == null) {
                    fg_history = new HistoryFragment();
                    fragmentTransaction.add(R.id.content, fg_history);
                } else {
                    fragmentTransaction.show(fg_history);
                }

                break;
            case 2:
                tx_trainstatus.setTextColor(dark);
                trainstatus_layout.setBackgroundColor(gray);
                titleTv.setText("当前进度");
                if (fg_trainstatus == null) {
                    fg_trainstatus = new TrainstatusFragment();
                    fragmentTransaction.add(R.id.content, fg_trainstatus);
                } else {
                    fragmentTransaction.show(fg_trainstatus);
                }

                break;
            case 3:
                tx_config.setTextColor(dark);
                config_layout.setBackgroundColor(gray);
                titleTv.setText("设 置");
                if (fg_config == null) {
                    fg_config = new ConfigFragment();
                    fragmentTransaction.add(R.id.content, fg_config);
                } else {
                    fragmentTransaction.show(fg_config);
                }
                break;
            case 4:
                tx_mine.setTextColor(dark);
                mine_layout.setBackgroundColor(gray);
                titleTv.setText("个人信息");
                if (fg_mine == null) {
                    fg_mine = new ClientInfoFragment();
                    fragmentTransaction.add(R.id.content, fg_mine);
                } else {
                    fragmentTransaction.show(fg_mine);
                }

                break;
        }
        fragmentTransaction.commit(); // 提交
    }
    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChoice() {
        tx_home.setTextColor(gray);
        home_layout.setBackgroundColor(whirt);
        tx_history.setTextColor(gray);
        history_layout.setBackgroundColor(whirt);
        tx_trainstatus.setTextColor(gray);
        trainstatus_layout.setBackgroundColor(whirt);
        tx_config.setTextColor(gray);
        config_layout.setBackgroundColor(whirt);
        tx_mine.setTextColor(gray);
        mine_layout.setBackgroundColor(whirt);
    }
    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (fg_home != null) {
            fragmentTransaction.hide(fg_home);
        }
        if (fg_history != null) {
            fragmentTransaction.hide(fg_history);
        }
        if (fg_trainstatus != null) {
            fragmentTransaction.hide(fg_trainstatus);
        }
        if (fg_config != null) {
            fragmentTransaction.hide(fg_config);
        }
        if (fg_mine != null){
            fragmentTransaction.hide(fg_mine);
        }
    }

}