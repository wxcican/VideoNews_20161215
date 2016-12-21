package com.fuicuiedu.idedemo.videonews_20161215;

import android.app.Application;

import com.fuicuiedu.idedemo.videonews_20161215.commons.ToastUtils;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class VideoNewsApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化吐丝工具类
        ToastUtils.init(this);
    }
}
