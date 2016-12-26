package com.fuicuiedu.idedemo.videonews_20161215.bombapi.result;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * 查询结果
 * 实体可以是新闻数据，可以是评论数据，所以做一个泛型
 */

public class QueryResult<Model> {

    private List<Model> results;

    public List<Model> getResults() {
        return results;
    }
}
