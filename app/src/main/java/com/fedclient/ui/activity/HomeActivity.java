package com.fedclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.TaskPublished;
import com.fedclient.R;
import com.fedclient.ui.adapter.TaskPublishedAdapter;
import com.fedclient.ui.fragment.Config_Fragment;
import com.fedclient.ui.fragment.History_Fragment;
import com.fedclient.ui.fragment.Home_Fragment;
import com.fedclient.ui.fragment.Mine_Fragment;
import com.fedclient.ui.fragment.Trainstatus_Fragment;
import com.fedclient.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

/**
 * 主页,用于展示已发布任务的信息
 */
public class HomeActivity extends FragmentActivity implements View.OnClickListener{
    private static final String TAG = "HomeActivity";
    private ImageView titleLeftImv;
    private TextView titleTv;
    private Home_Fragment fg_home;
    private History_Fragment fg_history;
    private Trainstatus_Fragment fg_trainstatus;
    private Config_Fragment fg_config;
    private Mine_Fragment fg_mine;


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

/*
    public ListView lv_project;
    private TaskPublishedAdapter list_item;
    List<TaskPublished> taskPublisheds= new ArrayList<TaskPublished>();
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        fragmentManager = getSupportFragmentManager();
        initView(); // 初始化界面控件
        setChioceItem(0); // 初始化页面加载时显示第一个选项卡

        //initComponents();
    }

    /**
     * 初始化组件及数据
     */
    /*private void initComponents(){
//        list.clear();
        lv_project = (ListView)findViewById(R.id.lv_project);

        *//**
         * 从服务端获取数据
         *//*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    HttpUrl url =HttpUrl.parse(UrlConstants.URL_TASK_GET_ALL_TASK).newBuilder()
                            .build();

                    HttpUtil.sendGet(url.toString(), new Callback() {
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String data = response.body().string();

                            Log.i(TAG, "onResponse: "+data);
                            Type userListType = new TypeToken<List<TaskPublished>>(){}.getType();
                            taskPublisheds= new Gson().fromJson(data,userListType);

                            if (taskPublisheds.isEmpty()){
                                Log.e(TAG, "onResponse: "+"taskPublisheds isEmpty");
                                taskPublisheds= new ArrayList<TaskPublished>();
                            }

                            Log.d(TAG, "onResponse: "+taskPublisheds.toString());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d(TAG, "onCreate: "+taskPublisheds.toString());
                                    //getApplicationContext()
                                    list_item = new TaskPublishedAdapter(HomeActivity.this,taskPublisheds,R.layout.view_tplist);
                                    lv_project.setAdapter(list_item);
                                }
                            });

                        }
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            Log.e(TAG, "onFailure: ", e);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }*/

    private void initView() {
// 初始化页面标题栏
        titleLeftImv = (ImageView) findViewById(R.id.title_imv);
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });
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

        home_layout.setOnClickListener(HomeActivity.this);
        history_layout.setOnClickListener(HomeActivity.this);
        trainstatus_layout.setOnClickListener(HomeActivity.this);
        config_layout.setOnClickListener(HomeActivity.this);
        mine_layout.setOnClickListener(HomeActivity.this);
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
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
                tx_home.setTextColor(dark);
                home_layout.setBackgroundColor(gray);
                titleTv.setText("主 页");
                if (fg_home == null) {
                    fg_home = new Home_Fragment();
                    fragmentTransaction.add(R.id.content, fg_home);
                } else {
                    fragmentTransaction.show(fg_home);
                }
                //Intent intent= new Intent(HomeActivity.this,HomeActivity.class);
                //startActivity(intent);
                break;
            case 1:
                tx_history.setTextColor(dark);
                history_layout.setBackgroundColor(gray);
                titleTv.setText("历史记录");
                if (fg_history == null) {
                    fg_history = new History_Fragment();
                    fragmentTransaction.add(R.id.content, fg_history);
                } else {
                    fragmentTransaction.show(fg_history);
                }
                //Intent intent1= new Intent(HomeActivity.this,HistoryActivity.class);
                //startActivity(intent1);
                break;
            case 2:
                tx_trainstatus.setTextColor(dark);
                trainstatus_layout.setBackgroundColor(gray);
                titleTv.setText("当前进度");
                if (fg_trainstatus == null) {
                    fg_trainstatus = new Trainstatus_Fragment();
                    fragmentTransaction.add(R.id.content, fg_trainstatus);
                } else {
                    fragmentTransaction.show(fg_trainstatus);
                }
                //Intent intent2= new Intent(HomeActivity.this,TrainStatusActivity.class);
                //startActivity(intent2);
                break;
            case 3:
// fourthImage.setImageResource(R.drawable.XXXX);
                tx_config.setTextColor(dark);
                config_layout.setBackgroundColor(gray);
                titleTv.setText("设 置");
                if (fg_config == null) {
                    fg_config = new Config_Fragment();
                    fragmentTransaction.add(R.id.content, fg_config);
                    Intent intent = new Intent(HomeActivity.this, ConfigActivity.class);//在Fragment中实现开启新的Activity
                    startActivity(intent);
                } else {
                    fragmentTransaction.show(fg_config);
                }
                //Intent intent3= new Intent(HomeActivity.this,ConfigActivity.class);
                //startActivity(intent3);
                break;
            case 4:
// fourthImage.setImageResource(R.drawable.XXXX);
                tx_mine.setTextColor(dark);
                mine_layout.setBackgroundColor(gray);
                titleTv.setText("个人信息");
                if (fg_mine == null) {
                    fg_mine = new Mine_Fragment();
                    fragmentTransaction.add(R.id.content, fg_mine);
                } else {
                    fragmentTransaction.show(fg_mine);
                }
                //Intent intent4= new Intent(HomeActivity.this,ClientInfoActivity.class);
                //startActivity(intent4);
                break;
        }
        fragmentTransaction.commit(); // 提交
    }
    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChioce() {
// firstImage.setImageResource(R.drawable.XXX);
        tx_home.setTextColor(gray);
        home_layout.setBackgroundColor(whirt);
// secondImage.setImageResource(R.drawable.XXX);
        tx_history.setTextColor(gray);
        history_layout.setBackgroundColor(whirt);
// thirdImage.setImageResource(R.drawable.XXX);
        tx_trainstatus.setTextColor(gray);
        trainstatus_layout.setBackgroundColor(whirt);
// fourthImage.setImageResource(R.drawable.XXX);
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