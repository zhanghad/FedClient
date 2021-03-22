package com.fedclient.util;

import com.google.gson.Gson;

import java.util.Iterator;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    /**
     *
     * @param address
     * @param callback
     */
    public static void sendGet(String address, okhttp3.Callback callback){
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     *
     * @param address
     * @param params
     * @param callback
     */
    public static void sendGet(String address, LinkedHashMap<String,String>params, okhttp3.Callback callback){
        // 执行GET请求，将请求结果回调到okhttp3.Callback中
        address = attachHttpGetParams(address,params);
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     *
     * @param address   地址
     * @param params  参数
     * @param callback
     */
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

    /**
     *
     * @param address   请求地址
     * @param object 请求体，对象
     * @param callback  回调函数
     */
    public static void sendPost(String address, Object object, okhttp3.Callback callback) {
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , new Gson().toJson(object));
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
