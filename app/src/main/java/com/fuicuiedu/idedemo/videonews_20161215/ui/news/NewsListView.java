package com.fuicuiedu.idedemo.videonews_20161215.ui.news;

import android.content.Context;
import android.util.AttributeSet;

import com.fuicuiedu.idedemo.videonews_20161215.bombapi.BombClient;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.NewsApi;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.entity.NewsEntity;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.result.QueryResult;
import com.fuicuiedu.idedemo.videonews_20161215.ui.base.BaseResourceView;

import retrofit2.Call;

/**
 * 视频新闻列表视图，使用BaseResourceView来完成
 */

public class NewsListView extends BaseResourceView<NewsEntity,NewsItemView>{
    public NewsListView(Context context) {
        super(context);
    }

    public NewsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected Call<QueryResult<NewsEntity>> queryData(int limit, int skip) {
        return newsApi.getVideoNewsList(limit,skip);
    }

    @Override
    protected int getLimit() {
        return 5;
    }

    @Override
    protected NewsItemView createItemView() {
        return new NewsItemView(getContext());
    }
}
