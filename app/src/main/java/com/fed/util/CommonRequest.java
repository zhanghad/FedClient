package com.fed.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CommonRequest {
    private String requestCode;

    private HashMap<String, String> requestParam;
    public CommonRequest(){
        requestCode = "";
        requestParam = new HashMap<>();
    }

    //请求代码
    public void setRequestCode(String requestCode) {
         this.requestCode = requestCode;
    }

    public void addRequestParam(String paramKey, String paramValue) {
        requestParam.put(paramKey, paramValue);
    }



    //组装成json字符串
    public String getJsonStr() {
        JSONObject object = new JSONObject();
        JSONObject param = new JSONObject(requestParam);
        try {
            // "requestCode"、"requestParam"是和服务器约定好的请求体字段名称
            object.put("requestCode", requestCode);
            object.put("requestParam", param);
        } catch (JSONException e) {
            Log.e("Request","请求报文组装异常：" + e.getMessage());
        }
        // 打印原始请求报文
        Log.d("Request",object.toString());
        return object.toString();
    }

}