package com.fuicuiedu.idedemo.videonews_20161215.bombapi.result;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

//{"code":202,"error":"username 'xc22' already taken."}

public class ErrorResult {

    private int code;
    private String error;

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }
}
