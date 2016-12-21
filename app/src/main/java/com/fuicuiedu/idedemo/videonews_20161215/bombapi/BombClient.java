package com.fuicuiedu.idedemo.videonews_20161215.bombapi;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class BombClient {
    private static BombClient bombClient;

    public static BombClient getInstance(){
        if (bombClient == null){
            bombClient = new BombClient();
        }
        return bombClient;
    }

    private OkHttpClient okHttpClient;

    private BombClient(){
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //构建OkHttp
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BombInterceptor())//添加Bomb必要的请求头的拦截器
                .addInterceptor(httpLoggingInterceptor)//添加日志拦截器
                .build();
    }

    //注册请求
    public Call register(String username, String password){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",username);
            jsonObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(null,jsonObject.toString());

        Request request = new Request.Builder()
                .url("https://api.bmob.cn/1/users")
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }
}
