package com.fuicuiedu.idedemo.videonews_20161215.bombapi.other;



//        "__type":"Pointer",
//        "className":"_User",
//        "objectId":用户Id

import com.google.gson.annotations.SerializedName;

public class AuthorPointer {

    @SerializedName("__type")
    private String type;
    private String className;
    private String objectId;

    public AuthorPointer(String userId) {
        type = "Pointer";
        className = "_User";
        this.objectId = userId;
    }
}
