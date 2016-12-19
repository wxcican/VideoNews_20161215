package com.fuicuiedu.idedemo.videoplayer.full;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.fuicuiedu.idedemo.videoplayer.R;

import io.vov.vitamio.widget.MediaController;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class CustomMediaController extends MediaController{
    private MediaPlayerControl mediaPlayerControl;//自定义视频控制器

    private AudioManager audioManager;//音频管理
    private Window window;//用于视频亮度管理

    private int maxVolume;//最大音量
    private int currentVolume;//当前音量
    private float currentBrightness;//当前亮度

    public CustomMediaController(Context context) {
        super(context);
        //音频管理
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        //最大音量
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //用于视频亮度管理
        window = ((Activity)context).getWindow();
    }

    //通过从写此方法，来自定义layout
    @Override
    protected View makeControllerView() {
        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.view_custom_video_controller,this
        );
        initView(view);
        return view;
    }

    //拿到自定义视频控制器
    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        super.setMediaPlayer(player);
        mediaPlayerControl = player;
    }

    //初始化视图,设置一些监听
    private void initView(View view){
        //开进快退的监听
        //屏幕亮度，音量的控制
    }
}
