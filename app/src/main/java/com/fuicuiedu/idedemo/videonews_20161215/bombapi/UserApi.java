package com.fuicuiedu.idedemo.videonews_20161215.bombapi;


import com.fuicuiedu.idedemo.videonews_20161215.bombapi.entity.UserEntity;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.result.UserResult;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 用户相关网络接口
 */

public interface UserApi {

    //用户注册
    @POST("1/users")
    Call<UserResult> register(@Body UserEntity userEntity);

    //用户登录
    @GET("1/login")
    Call<UserResult> login(@Query("username") String username, @Query("password") String password);

}
