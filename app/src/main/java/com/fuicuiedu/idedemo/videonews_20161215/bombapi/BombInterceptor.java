package com.fuicuiedu.idedemo.videonews_20161215.bombapi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 处理Bomb需要的统一的头字段
 */

public class BombInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //拿到请求消息
        Request request = chain.request();
        //拿到请求消息的构造器
        Request.Builder builder = request.newBuilder();

        // 用于让bomb服务器，区分是哪一个应用
        builder.addHeader("X-Bmob-Application-Id", BombConst.APPLICATION_ID);
        // 用于授权
        builder.addHeader("X-Bmob-REST-API-Key", BombConst.REST_API_KEY);
        // 请求和响应都统一使用json格式
        builder.addHeader("Content-Type","application/json");

        //构建得到添加完请求头的请求消息
        request = builder.build();
        //执行请求拿到响应消息
        Response response = chain.proceed(request);
        return response;
    }
}
