package com.fuicuiedu.idedemo.videonews_20161215.bombapi.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

//{
//        "author": {
//        "__type": "Pointer",
//        "className": "_User",
//        "objectId": "79e992125d"
//        },
//        "content": "吃葡萄不吐葡萄皮",
//        "createdAt": "2016-07-18 15:29:04",
//        "news": {
//        "__type": "Pointer",
//        "className": "News",
//        "objectId": "IPPmF99F"
//        },
//        "objectId": "26ec97caa4",
//        "updatedAt": "2016-07-18 15:29:04"
//        }

public class CommentsEntity {

    private String objectId;
    private String content;
    private AuthorEntity author;
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getContent() {
        return content;
    }

    public AuthorEntity getAuthor() {
        return author;
    }
}
