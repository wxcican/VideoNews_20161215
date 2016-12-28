package com.fuicuiedu.idedemo.videonews_20161215.ui.local;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;

import com.fuicuiedu.idedemo.videonews_20161215.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class LocalVideoAdapter extends CursorAdapter{

    // 用来加载视频预览图的线程池
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);


    public LocalVideoAdapter(Context context) {
        super(context,null,true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return new LocalVideoItem(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final LocalVideoItem item = (LocalVideoItem) view;
        item.bind(cursor);

        //拿到文件路径
        final String filePath = item.getFilePath();
        //后台线程获取视频预览图
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //加载视频的预览图像
                Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Video.Thumbnails.MINI_KIND);

                //将预览图设置到控件上
                //注意，当前是在后台线程
                item.setIvPreView(filePath,bitmap);
            }
        });
    }

    //关闭线程池
    public void release(){
        executorService.shutdown();
    }
}
