package com.fed.util;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    //get(无参数
    public static void sendRequest(String address, okhttp3.Callback callback){
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //get(参数为map字符串
    public static void sendRequest(String address, LinkedHashMap<String,String>params, okhttp3.Callback callback){
        // 执行GET请求，将请求结果回调到okhttp3.Callback中
        address = attachHttpGetParams(address,params);
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //post(参数 字符串为map
    public static void sendPost(String address,LinkedHashMap<String,String> params, okhttp3.Callback callback){
        FormBody.Builder builder = new FormBody.Builder();
        // builder填充参数，构造请求体
        Iterator<String> keys = params.keySet().iterator();
        Iterator<String> values = params.values().iterator();
        for (int i=0;i<params.size();i++){
            builder.add(keys.next(),values.next());
            System.out.println(i);
        }
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //post(参数 json
    public static void sendPost(String address, String json, okhttp3.Callback callback) {
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(JSON, json);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    private static String attachHttpGetParams(String url, LinkedHashMap<String,String> params){
        Iterator<String> keys = params.keySet().iterator();
        Iterator<String> values = params.values().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");

        for (int i=0;i<params.size();i++ ) {
            stringBuffer.append(keys.next()+"="+values.next());
            if (i!=params.size()-1) {
                stringBuffer.append("&");
            }
        }
        System.out.println("url:"+ url + stringBuffer.toString());
        return url + stringBuffer.toString();
    }

}
