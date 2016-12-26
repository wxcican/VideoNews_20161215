package com.fuicuiedu.idedemo.videonews_20161215.bombapi;

import com.fuicuiedu.idedemo.videonews_20161215.bombapi.entity.NewsEntity;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.result.QueryResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static android.R.attr.order;

/**
 * 新闻的相关接口
 */

public interface NewsApi {

    //获取新闻列表,排序方式，接时间新到旧排序
    @GET("1/classes/News?order=-createdAt")
    Call<QueryResult<NewsEntity>> getVideoNewsList(@Query("limit") int limit, @Query("skip") int skip);

}
