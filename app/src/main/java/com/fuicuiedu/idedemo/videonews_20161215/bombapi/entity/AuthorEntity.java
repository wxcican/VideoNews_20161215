package com.fuicuiedu.idedemo.videonews_20161215.bombapi.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

//"__type": "Pointer",
//         "objectId":用户id,
//         "username":用户名称,

public class AuthorEntity {

    @SerializedName("__type")
    private String type;
    private String objectId;
    private String username;


    public String getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getUsername() {
        return username;
    }
}
