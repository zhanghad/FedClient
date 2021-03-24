package com.fedclient.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.TaskPublished;
import com.fedclient.R;
import com.fedclient.ui.adapter.TaskPublishedAdapter;
import com.fedclient.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

/**
 * 主页,用于展示已发布任务的信息
 */
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private RadioButton RB_history;
    private RadioButton RB_now;
    private RadioButton RB_config;
    private RadioButton RB_mine;
    public ListView lv_project;
    private TaskPublishedAdapter list_item;
    List<TaskPublished> taskPublisheds= new ArrayList<TaskPublished>();

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    list_item.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initComponents();
        setListeners();
    }


    /**
     * 初始化组件及数据
     */
    private void initComponents(){
//        list.clear();
        lv_project = (ListView)findViewById(R.id.lv_project);
        RB_history = (RadioButton) findViewById (R.id.RB_history);
        RB_now = (RadioButton) findViewById (R.id.RB_now);
        RB_config = (RadioButton) findViewById (R.id.RB_shezhi);
        RB_mine = (RadioButton) findViewById (R.id.RB_mine);

        /**
         * 从服务端获取数据
         */
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

                            Type userListType = new TypeToken<List<TaskPublished>>(){}.getType();
                            taskPublisheds= new Gson().fromJson(data,userListType);

                            if (taskPublisheds.isEmpty()){
                                Log.e(TAG, "onResponse: "+"taskPublisheds isEmpty");
                                taskPublisheds= new ArrayList<TaskPublished>();
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d(TAG, "onCreate: "+taskPublisheds.toString());
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



    }

    /**
     * 设置监听器
     */
    private void setListeners(){
        RB_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,HistoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, TrainStatusActivity.class);
                startActivity(intent);
                finish();
            }
        });
        RB_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ConfigActivity.class);
                startActivity(intent);
                finish();
            }
        });

        RB_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ClientInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }






}