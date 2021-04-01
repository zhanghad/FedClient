package com.fedclient.util;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.fedclient.manager.ClientManager;
import com.fedclient.trash.Consts;

import org.datavec.api.records.impl.Record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Server {
    private Activity mActivity;
    private String resCode;
    private String resMsg;
    private HashMap<String,String> property;
    private ArrayList<HashMap<String,String>> dataList;
    private final long sleepTime = 700L;

    public Server(Activity activity){
        mActivity = activity;
    }

    //User属性获取 设置（服务器端）
    public String getNickname(){
        CommonRequest request = new CommonRequest();
        request.setRequestCode(Consts.REQUESTCODE_NICKNAME);
        try {
            infoPost(Consts.URL_GetInfo, request.getJsonStr());
            Thread.sleep(sleepTime);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        String nickname = property.get("nickname");
        property.clear();
        return nickname;
    }

    public String getUserscore(){
        CommonRequest request = new CommonRequest();
        request.setRequestCode(Consts.REQUESTCODE_USERCORE);
        try {
            infoPost(Consts.URL_GetInfo, request.getJsonStr());
            Thread.sleep(sleepTime);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        String userscore = property.get("userscore");
        property.clear();
        return userscore;
    }

    private void infoPost(String url, String json){
        HttpUtil.sendPost(url,json,new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CommonResponse res = new CommonResponse(response.body().string());
                resCode = res.getResCode();
                resMsg = res.getResMsg();
                property = res.getPropertyMap();
                dataList = res.getDataList();
//                showResponse(resMsg);
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                showResponse("Network ERROR");
            }
        });
    }

    private void showResponse(final String msg) {
        Log.e("Server",msg);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String setNickname(String nickname){
        CommonRequest request = new CommonRequest();
        request.setRequestCode(Consts.REQUESTCODE_NICKNAME);
        request.addRequestParam("loginName", ClientManager.getCurrentClient().getClientName());
        request.addRequestParam("clientName",nickname);
        try {
            infoPost(Consts.URL_SetInfo,request.getJsonStr());
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resCode;
    }

    public String setUserscore(String userscore){
        CommonRequest request = new CommonRequest();
        request.setRequestCode(Consts.REQUESTCODE_USERCORE);
        request.addRequestParam("username", ClientManager.getCurrentClient().getLoginName());
        request.addRequestParam("userscore",userscore);
        try {
            infoPost(Consts.URL_SetInfo,request.getJsonStr());
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resCode;
    }

    public String setLoginName(String account) {
        CommonRequest request = new CommonRequest();
        request.setRequestCode(Consts.REQUESTCODE_LOGINNAME);
        request.addRequestParam("loginName", ClientManager.getCurrentClient().getClientName());
        try {
            infoPost(Consts.URL_SetInfo,request.getJsonStr());
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resCode;

    }

    public String setPhonenumber(String phone) {
        CommonRequest request = new CommonRequest();
        request.setRequestCode(Consts.REQUESTCODE_PHONE);
        request.addRequestParam("loginName", ClientManager.getCurrentClient().getClientName());
        request.addRequestParam("phone",phone);
        try {
            infoPost(Consts.URL_SetInfo,request.getJsonStr());
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resCode;
    }

    public String setSex(String sex) {
        CommonRequest request = new CommonRequest();
        request.setRequestCode(Consts.REQUESTCODE_PHONE);
        request.addRequestParam("loginName", ClientManager.getCurrentClient().getClientName());
        request.addRequestParam("sex",sex);
        try {
            infoPost(Consts.URL_SetInfo,request.getJsonStr());
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resCode;
    }

    public String setEmail(String email) {
        CommonRequest request = new CommonRequest();
        request.setRequestCode(Consts.REQUESTCODE_PHONE);
        request.addRequestParam("loginName", ClientManager.getCurrentClient().getClientName());
        request.addRequestParam("email",email);
        try {
            infoPost(Consts.URL_SetInfo,request.getJsonStr());
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resCode;
    }


    public ArrayList<Record> getRecords(){
        CommonRequest request = new CommonRequest();
        request.setRequestCode(Consts.REQUESTCODE_RECORD);
        request.addRequestParam("username", ClientManager.getCurrentClient().getLoginName());
        try {
            infoPost(Consts.URL_GetInfo, request.getJsonStr());
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Record> recordList = new ArrayList<>();
        for(HashMap<String,String> dataMap:dataList){
            //Record record = new Record();
           // record.setUser(ClientManager.getCurrentClient());

            //record.save();// 同时存入本地数据库
           // recordList.add(record);
        }
        dataList.clear();
        return recordList;
    }


}

