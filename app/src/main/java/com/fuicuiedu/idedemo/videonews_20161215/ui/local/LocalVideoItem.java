package com.fuicuiedu.idedemo.videonews_20161215.ui.local;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuicuiedu.idedemo.videonews_20161215.R;
import com.fuicuiedu.idedemo.videoplayer.full.VideoViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class LocalVideoItem extends FrameLayout {
    public LocalVideoItem(Context context) {
        this(context, null);
    }

    public LocalVideoItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LocalVideoItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @BindView(R.id.ivPreview)
    ImageView ivPreview;
    @BindView(R.id.tvVideoName)
    TextView tvVideoName;
    private String filePath;//文件路径

    public String getFilePath(){
        return filePath;
    }

    public void setIvPreView(Bitmap bitmap){
        ivPreview.setImageBitmap(bitmap);
    }

    //设置预览图,可以在后台线程执行
    public void setIvPreView(String filePath, final Bitmap bitmap){
        if (!filePath.equals(this.filePath)) return;
        post(new Runnable() {
            @Override
            public void run() {
                ivPreview.setImageBitmap(bitmap);
            }
        });
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_local_video, this, true);
        ButterKnife.bind(this);
    }

    public void bind(Cursor cursor) {
        //取出视频名称
        String videoName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
        tvVideoName.setText(videoName);
        //取出文件路径
        filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));

        //获取视频预览图，是一个很费时的操作
        // ------到后台线程来执行

        //同时获取多张图片，可能会同时有多个线程
        //------- 线程池的处理

        //获取过的图片，做缓存处理
        // ------LruCache（最近最少使用原则）

        //获取预览图
//        Bitmap bimap = ThumbnailUtils.createVideoThumbnail(filePath,MediaStore.Video.Thumbnails.MINI_KIND);
        //设置预览图
//        ivPreview.setImageBitmap(bimap);
    }

    //全屏播放
    @OnClick
    public void click(){
        VideoViewActivity.open(getContext(),filePath);
    }
}
