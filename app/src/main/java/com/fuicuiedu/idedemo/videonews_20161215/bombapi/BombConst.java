package com.fuicuiedu.idedemo.videonews_20161215.bombapi;

/**
 * 网络连接模块用到的常量值
 */

public interface BombConst {
    // 应用Id，让Bomb后端区分是哪一个应用
    String APPLICATION_ID = "623aaef127882aed89b9faa348451da3";

    // REST API的授权码
    String REST_API_KEY = "c00104962a9b67916e8cbcb9157255de";

    // 服务器的评论表表名
    String TABLE_COMMENTS = "Comments";
    // 服务器的评论表新闻字段(评论所对应的新闻)
    String FIELD_NEWS = "news";

    // 服务器的新闻表表名
    String TABLE_NEWS = "News";
    // 服务器的新闻表收藏字段(此新闻被谁收藏)
    String FIELD_LIKES = "likes";

    // 服务器的用户表表名
    String TABLE_USER = "_User";

    // 服务器的收藏表表名
    String TABLE_LIKES = "likes";
}
