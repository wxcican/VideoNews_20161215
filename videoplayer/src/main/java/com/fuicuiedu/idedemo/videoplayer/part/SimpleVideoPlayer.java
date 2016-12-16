package com.fuicuiedu.idedemo.videoplayer.part;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.fuicuiedu.idedemo.videoplayer.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

/**
 * 一个自定义的VideoView，使用MdeiaPlayer+SurfaceView来实现视频的播放
 * MediaPlayer来做视频播放的控制，SurfaceView来显示视频
 * 视图方面（initView初始化）简单实现：一个播放/暂停按钮，一个进度条，一个屏按钮，一个SurfaceView
 *
 * 结构：
 * 提供setVideoPath方法：设置数据源
 * 提供OnResume方法(在activity的onResume调用)：初始化MediaPlayer，准备MediaPlayer
 * 提供OnPause方法(在activity的onPause调用)：释放mediaplayer，暂停mediaplayer
 */

public class SimpleVideoPlayer extends FrameLayout{


    private String videoPath;//视频播放Url
    private MediaPlayer mediaPlayer;
    private boolean isPrepared;//是否准备好
    private boolean isPlaying;//是否正在播放

    //视图相关
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private ImageView ivPreview;//预览图
    private ImageButton btnToggle;//播放，暂停
    private ProgressBar progressBar;//进度条

    public SimpleVideoPlayer(Context context) {
        super(context,null);
    }

    public SimpleVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public SimpleVideoPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //做视图相关的初始化
    private void init(){
        //Vitamio初始化
        Vitamio.isInitialized(getContext());
        //填充布局
        LayoutInflater.from(getContext()).inflate(R.layout.view_simple_video_player,this,true);
        //初始化SurfaceView
        initSurfaceView();
        //初始化视频播放控制视图
        initControllerViews();
    }

    //设置数据源
    public void setVideoPath(String videoPath){
        this.videoPath = videoPath;
    }

    //提供OnResume方法(在activity的onResume调用)
    public void onResume(){
        //：初始化MediaPlayer，
        initMediaPlayer();
        //准备MediaPlayer
        prepareMediaPlater();
    }

    //提供OnPause方法(在activity的onPause调用)，
    public void onPause(){
        //暂停mediaplayer
        pauseMdeiaPlayer();
        //：释放mediaplayer
        releaseMediaPlayer();
    }

}
