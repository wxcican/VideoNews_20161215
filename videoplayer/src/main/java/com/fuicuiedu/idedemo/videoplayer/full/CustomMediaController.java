package com.fuicuiedu.idedemo.videoplayer.full;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.fuicuiedu.idedemo.videoplayer.R;

import io.vov.vitamio.widget.MediaController;

import static android.R.attr.start;
import static android.R.attr.startX;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class CustomMediaController extends MediaController {
    private MediaPlayerControl mediaPlayerControl;//视频播放控制接口，用于管理视频进度

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
        window = ((Activity) context).getWindow();
    }

    //通过从写此方法，来自定义layout
    @Override
    protected View makeControllerView() {
        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.view_custom_video_controller, this);
        initView(view);
        return view;
    }

    //视频播放控制接口，用于管理视频进度
    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        super.setMediaPlayer(player);
        mediaPlayerControl = player;
    }

    //初始化视图,设置一些监听
    //开进快退的监听
    //屏幕亮度，音量的控制
    private void initView(View view) {
        //快进
        ImageButton btnFastForward = (ImageButton) view.findViewById(R.id.btnFastForward);
        btnFastForward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //拿到当前播放位置
                long postion = mediaPlayerControl.getCurrentPosition();
                //快进10秒
                postion += 10000;
                //如果快进10秒后，大于等于总的视频长度，则到头
                if (postion >= mediaPlayerControl.getDuration()) {
                    postion = mediaPlayerControl.getDuration();
                }
                //否则移动到快进的位置
                mediaPlayerControl.seekTo(postion);
            }
        });
        //快退
        ImageButton btnFastRewind = (ImageButton) view.findViewById(R.id.btnFastRewind);
        btnFastRewind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                long postion = mediaPlayerControl.getCurrentPosition();
                postion -= 10000;
                if (postion < 0) {
                    postion = 0;
                }
                mediaPlayerControl.seekTo(postion);
            }
        });

        //调整视图（左边调整亮度，右边调整音量）
        final View adjustView = view.findViewById(R.id.adjustView);
        //依赖GestureDetector（手势识别类）,来进行划屏调整音量和亮度的手势处理
        final GestureDetector gestureDetector = new GestureDetector(
                getContext(),new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                float startX = e1.getX();//开始的X轴坐标
                float startY = e1.getY();//开始的Y轴坐标
                float endX = e2.getX();//结束的X轴坐标
                float endY = e2.getY();//结束的Y轴坐标
                float width = adjustView.getWidth();//调整视图的宽
                float height = adjustView.getHeight();//调整视图的高
                float percentage = (startY - endY) / height;//高度滑动的百分比
                //左侧：调整亮度(在屏幕左侧的1/5)
                if (startX < width /5){
                    adjustBrightness(percentage);//调整亮度的方法
                }
                //右侧：调整音量(在屏幕右侧的1/5)
                else if (startX > width * 4/5){
                    adjustVolume(percentage);//调整音量的方法
                }
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
        //对adjustView（调整视图）进行touch监听
        //但是，我们自己不去判断处理各种touch动用了什么，都交给gestureDetector去做
        adjustView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //当用户摁下的时候
                if((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){
                    //拿到当前音量
                    currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    //拿到当前亮度
                    currentBrightness = window.getAttributes().screenBrightness;
                }
                //交给gestureDetector去做
                gestureDetector.onTouchEvent(event);
                //在调整过程中，CustomMediaController一直显示
                show();
                return true;
            }
        });
    }

    //调整音量的方法
    private void adjustVolume(float percentage){
        //最终音量 = 最大音量 * 改变的百分比 + 当前音量
        int volume = (int) ((maxVolume * percentage) + currentVolume);
        //如果最终音量大于最大音量，结果为最大音量
        volume = volume > maxVolume ? maxVolume : volume;
        //如果最终音量小于0，结果为0
        volume = volume < 0 ? 0 : volume;
        //设置音量
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volume,AudioManager.FLAG_SHOW_UI);
    }

    //调整亮度的方法（最小亮度 = 0 ，最大亮度 = 1.0f）
    private void adjustBrightness(float percentage){
        //最终亮度 = percentage + 当前亮度
        float brightness = percentage + currentBrightness;
        //大于最大亮度
        brightness = brightness > 1.0f ? 1.0f : brightness;
        //小于最小亮度
        brightness = brightness < 0 ? 0 : brightness;
        //设置亮度
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.screenBrightness = brightness;
        window.setAttributes(layoutParams);
    }

}
