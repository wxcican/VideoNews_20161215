package com.fuicuiedu.idedemo.videonews_20161215.bombapi;

import com.fuicuiedu.idedemo.videonews_20161215.bombapi.entity.CommentsEntity;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.entity.NewsEntity;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.other.InQuery;
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

    //获取新闻的所有评论,接时间新到旧排序
    //注意，我们希望评论作者不仅返回objectId，还返回username，可以使用URL编码参数include=author。
    @GET("1/classes/Comments?include=author&order=-createdAt")
    Call<QueryResult<CommentsEntity>> getComments(
            @Query("limit") int limit,
            @Query("skip") int skip,
            @Query("where") InQuery where);
}
