package com.fuicuiedu.idedemo.videonews_20161215.bombapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

import static com.fuicuiedu.idedemo.videonews_20161215.R.string.register;

/**
 * 用户相关网络接口
 */

public interface UserApi {

    //用户注册
    @POST("1/users")
//    Call register(请求体);


    @GET("1/login")
    Call login(@Query("username") String username, @Query("password") String password);


}
