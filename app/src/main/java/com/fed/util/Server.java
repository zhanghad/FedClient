package com.fed.util;


import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.fed.Data.Record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
}

