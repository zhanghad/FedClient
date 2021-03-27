package com.fedclient.fed.websocket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;


import com.fedclient.constants.AndroidConstants;
import com.fedclient.constants.UrlConstants;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketService extends Service {

    private static final String TAG = "WebSocketController";
    private String wsUrl = UrlConstants.WS_TEST;
    private FedWebSocketClient fedWebSocketClient;

    public WebSocketService(){}

    public WebSocketService(String wsUrl){
        this.wsUrl=wsUrl;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        //实例化websocket
        try {
            Log.i(TAG, "onStartCommand: try");
            fedWebSocketClient=new FedWebSocketClient(new URI(wsUrl));
            fedWebSocketClient.connect();
        } catch (URISyntaxException e) {
            Log.e(TAG, "onStartCommand: " );
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");

        //关闭连接
        if(fedWebSocketClient==null){
            Log.d(TAG, "onDestroy: websocket is null");
        }else {
            fedWebSocketClient.close();
        }
        super.onDestroy();
    }

}