package com.fuicuiedu.idedemo.videonews_20161215.bombapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static android.R.id.list;

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
    private Retrofit retrofit;
    private Retrofit retrofit_cloud;//用于新接口
    private UserApi userApi;
    private NewsApi newsApi;
    private NewsApi newsApi_cloud;//用于新接口

    private BombClient(){
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //构建OkHttp
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BombInterceptor())//添加Bomb必要的请求头的拦截器
                .addInterceptor(httpLoggingInterceptor)//添加日志拦截器
                .build();

        //让Gson能够将Bomb返回的时间戳自动转换为Date对象
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        //构建Retrofit
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                //bomb服务器的BaseUrl
                .baseUrl("https://api.bmob.cn/")
                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //构建retrofit_cloud
        retrofit_cloud = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://cloud.bmob.cn/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    //拿到UserApi
    public UserApi getUserApi(){
        if (userApi == null){
            userApi = retrofit.create(UserApi.class);
        }
        return userApi;
    }

    //拿到newsApi
    public NewsApi getNewsApi(){
        if (newsApi == null){
            newsApi = retrofit.create(NewsApi.class);
        }
        return newsApi;
    }

    //拿到newsApi_cloud
    public NewsApi getNewsApi_cloud(){
        if (newsApi_cloud == null){
            newsApi_cloud = retrofit_cloud.create(NewsApi.class);
        }
        return newsApi_cloud;
    }
}
