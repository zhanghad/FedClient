package com.fedclient.fed.websocket;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;


import com.fedclient.constants.UrlConstants;
import com.fedclient.manager.TaskServiceManager;
import com.fedclient.ui.callback.TaskCallback;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class WebSocketService extends Service implements TaskCallback {

    private static final String TAG = "WebSocketController";
    private String wsUrl = UrlConstants.WS_TEST;
    private FedWebSocketClient fedWebSocketClient;
    private List<String> messageList = new ArrayList<String>();

    public static final String ACTION_NAME="com.fedclient.fed.websocket.WebSocketService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new WebSocketServiceBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        TaskServiceManager.setWebSocketService(this);


        Log.i(TAG, "onStartCommand: ");
        messageList.clear();
        //实例化websocket
        try {
            Log.i(TAG, "onStartCommand: try");
            fedWebSocketClient=new FedWebSocketClient(new URI(wsUrl),this);
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


    /**
     * 接受信息，并通知activity更新ui
     * @param message
     */
    @Override
    public void sendMessage(String message) {
        Log.i(TAG, "sendMessage: "+message);
        messageList.add(message);
        Intent intent =new Intent();
        intent.setAction(ACTION_NAME);
        intent.putExtra("message",message);
        sendBroadcast(intent);
    }


    public List<String> getMessageList(){
        return messageList;
    }


    public class WebSocketServiceBinder extends Binder{
        public WebSocketService getService(){
            return WebSocketService.this;
        }
    }

}
