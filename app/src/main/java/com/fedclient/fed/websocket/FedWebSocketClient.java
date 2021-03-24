package com.fedclient.fed.websocket;

import android.util.Log;

import com.fedclient.fed.message.Message;
import com.fedclient.fed.train.model.MultiRegression;
import com.fedclient.ui.callback.TaskCallback;
import com.fedclient.util.ByteBufferUtil;
import com.fedclient.fed.message.Constant;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;


public class FedWebSocketClient extends WebSocketClient {

    private static final String TAG = "FedWebSocketClient";
    private Message clientMessage=new Message();
    private MultiRegression multiRegression = new MultiRegression();

    private TaskCallback taskCallback;

    public FedWebSocketClient(URI serverUri,TaskCallback t) {
        super(serverUri);
        this.taskCallback=t;
    }

    @Override
    public void send(String text) {
        super.send(text);
    }

    @Override
    public void send(ByteBuffer bytes) {
        super.send(bytes);
    }
    

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.i(TAG, "onOpen: " + handshakedata);
    }

    @Override
    public void onMessage(String message) {
        //接收服务端指令
        if (message.equals(Constant.ALL_CLIENT_START)) {

            Log.i(TAG, "onMessage: train start");
            sendMessage(taskCallback,"server command:train start");
            //等待接收全局梯度
            while (!multiRegression.RECEIVED_GLOBAL) ;
            multiRegression.RECEIVED_GLOBAL = false;
            Log.i(TAG, "onMessage: RECEIVED_GLOBAL");
            sendMessage(taskCallback,"server command:receive global model");
            //用全局模型更新本地模型
            try{
                sendMessage(taskCallback,"server command:training");
                multiRegression.execute();
            }catch(Exception e){
                e.printStackTrace();
            }

            //上传模型
            try {
                Log.i(TAG, "onMessage: 上传模型");
                sendMessage(taskCallback,"server command:upload model");
                clientMessage.setMessage(Constant.CLIENT,Constant.CLIENT_UPDATE_WEIGHT,null,multiRegression.getWeight());
                ByteBuffer byteBuffer = ByteBufferUtil.getByteBuffer(clientMessage);
                send(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (message.equals(Constant.ALL_CLIENT_STOP)) {
            //服务器通知结束训练
            Log.i(TAG, "onMessage: train stop ");
            sendMessage(taskCallback,"server command:train stop");
        }else if (message.equals("success")){
            Log.i(TAG, "onMessage: train success");
            sendMessage(taskCallback,"train success");
        }


    }


    @Override
    public void onMessage(ByteBuffer message) {
        Log.i(TAG, "onMessage: receiving global");
        sendMessage(taskCallback,"receiving global model");

        Object object = null;
        try {
            if(message==null){
                Log.e(TAG, "onMessage: message==null");
            }else {
                object = ByteBufferUtil.getObject(message);
                Log.i(TAG, "onMessage: ByteBufferUtil"+object.getClass()+" "+object);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }


        Message tempMessage=(Message)object;
        Log.i(TAG, "onMessage: 更新本地模型");
        sendMessage(taskCallback,"update local model");


        assert tempMessage != null;
        if(tempMessage.owner==Constant.SERVER){
            switch (tempMessage.order){
                case Constant.SERVER_GLOBAL_MODEL:
                    Log.i(TAG, "onMessage: SERVER_GLOBAL_MODEL");
                    multiRegression.updateModel(tempMessage.model);
                    break;
                case Constant.SERVER_GLOBAL_WEIGHT:
                    Log.i(TAG, "onMessage: SERVER_GLOBAL_WEIGHT");
                    multiRegression.updateModel(tempMessage.weight);
                    break;
                default:
                    Log.i(TAG, "onMessage: \n"+tempMessage);
                    break;
            }
            Log.i(TAG, "onMessage: RECEIVED_GLOBAL = true");
            multiRegression.RECEIVED_GLOBAL = true;
        }


    }


    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i(TAG, "onClose: " + code + " \n" + reason);
        sendMessage(taskCallback,"websocket close!\nreason:"+reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        sendMessage(taskCallback,"websocket error!\n"+ex);
    }


    /**
     * 通过回调函数反向传递消息
     * @param taskCallback
     * @param message
     */
    public void sendMessage(TaskCallback taskCallback,String message){
        taskCallback.sendMessage(message);
    }
}
