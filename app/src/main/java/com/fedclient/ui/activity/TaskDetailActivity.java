package com.fedclient.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import com.fedclient.R;
import com.fedclient.constants.UrlConstants;
import com.fedclient.domain.TaskConfig;
import com.fedclient.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

public class TaskDetailActivity extends AppCompatActivity {
    private static final String TAG = "TaskDetailActivity";
    private TextView tv_td_title;
    private TextView tv_td_id;
    private TextView tv_td_status;
    private TextView tv_td_curclients;
    private TextView tv_td_curepoch;
    private TextView tv_td_taskdetail;
    private TextView tv_td_dataformat;
    private TextView tv_td_hardware;
    private RadioButton rb_td_back;
    private RadioButton rb_td_join;

    private Long tpId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetail);
        initComponents();
        setListeners();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();
        
        Intent intent=getIntent();
//        Log.d(TAG, "onStart: "+intent);
//        Log.d(TAG, "onStart: "+intent.getStringExtra("name"));
//        Log.d(TAG, "onStart: "+tv_td_title+"\n"+tv_td_id+"\n"+tv_td_status+"\n"+tv_td_curclients+"\n"+tv_td_curepoch);

        tv_td_title.setText(intent.getStringExtra("name"));
        tv_td_id.setText(intent.getLongExtra("id",0L)+"");
        tv_td_status.setText(intent.getStringExtra("status"));
        tv_td_curclients.setText((intent.getLongExtra("curclient",0L))+"");
        tv_td_curepoch.setText(intent.getLongExtra("curepoch",0L)+"");


        tpId=intent.getLongExtra("id",0L);

        //从服务端获取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    HttpUrl url =HttpUrl.parse(UrlConstants.URL_TASK_GET_TASK_CONFIG).newBuilder()
                            .addQueryParameter("tp_id",tpId.toString())
                            .build();

                    HttpUtil.sendGet(url.toString(), new Callback() {
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String data = response.body().string();
                            Log.i(TAG, "onResponse: "+data);
                            Type configType = new TypeToken<TaskConfig>(){}.getType();

                            Gson gson=new GsonBuilder()
                                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .create();
                            TaskConfig taskConfig= gson.fromJson(data,configType);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_td_dataformat.setText(taskConfig.getDataFormat());
                                    tv_td_hardware.setText(taskConfig.getDeviceRequire());
                                    tv_td_taskdetail.setText(taskConfig.getTcDescription());
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
     * 初始化组件及数据
     */
    private void initComponents(){
        tv_td_title=(TextView)findViewById(R.id.tv_td_title);
        Log.d(TAG, "initComponents: "+tv_td_title);
        tv_td_id=(TextView)findViewById(R.id.tv_td_id);
        tv_td_status=(TextView)findViewById(R.id.tv_td_status);
        tv_td_curclients=(TextView)findViewById(R.id.tv_td_curclients);
        tv_td_curepoch=(TextView)findViewById(R.id.tv_td_curepoch);
        tv_td_taskdetail=(TextView)findViewById(R.id.tv_td_taskdetail);
        tv_td_dataformat=(TextView)findViewById(R.id.tv_td_dataformat);
        tv_td_hardware=(TextView)findViewById(R.id.tv_td_hardware);
        rb_td_back=(RadioButton) findViewById(R.id.rb_td_back);
        rb_td_join =(RadioButton)findViewById(R.id.rb_td_join);

    }


    /**
     * 设置监听器
     */
    private void setListeners(){
        rb_td_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TaskDetailActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rb_td_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TaskDetailActivity.this,DataActivity.class);
                intent.putExtra("tpId",tpId);
                startActivity(intent);
                finish();
            }
        });



    }

}
