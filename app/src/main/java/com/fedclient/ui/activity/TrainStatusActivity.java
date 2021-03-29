package com.fedclient.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.fedclient.R;
import com.fedclient.service.WebSocketService;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TrainStatusActivity extends AppCompatActivity{
    private ImageView iv_back;
    private TextView tv_message;
    List<String> messageList;

//    private Intent serviceIntent; //服务意图
    private WebSocketReceiver webSocketReceiver; //广播接收者
    private IntentFilter intentFilter; //意图过滤器

//    private WebSocketService webSocketService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_trainstatus);
        initComponents();
        setListeners();

/*        Intent intent = new Intent("com.example.communication.MSG_ACTION");
        bindService(intent, conn, Context.BIND_AUTO_CREATE);*/
    }


    @Override
    protected void onStart() {
        super.onStart();

//        messageList=TaskServiceManager.getWebSocketService().getMessageList();

    }

    /**
     * 初始化组件及数据
     */
    private void initComponents(){
        iv_back = (ImageView) findViewById(R.id.iv_ts_back);
        tv_message=(TextView) findViewById(R.id.tv_ts_message);

//        serviceIntent=new Intent(TrainStatusActivity.this,WebSocketService.class);

        webSocketReceiver = new WebSocketReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(WebSocketService.ACTION_NAME);
        registerReceiver(webSocketReceiver,intentFilter);
    }


    /**
     * 设置监听器
     */
    private void setListeners(){
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrainStatusActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


/*    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //返回一个MsgService对象
            webSocketService = ((WebSocketService.WebSocketServiceBinder)service).getService();

        }
    };*/

    /**
     * 广播接受
     */
    class WebSocketReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_message.setText(intent.getStringExtra("message"));
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
//        unbindService(conn);
        unregisterReceiver(webSocketReceiver);
        super.onDestroy();
    }




}