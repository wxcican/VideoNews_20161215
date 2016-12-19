package com.fuicuiedu.idedemo.videonews_20161215;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fuicuiedu.idedemo.videoplayer.full.VideoViewActivity;
import com.fuicuiedu.idedemo.videoplayer.part.SimpleVideoPlayer;

public class MainActivity extends AppCompatActivity {

    SimpleVideoPlayer simpleVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleVideoPlayer = (SimpleVideoPlayer) findViewById(R.id.main_svp);
        simpleVideoPlayer.setVideoPath(VideoUrlRes.getTestUrl1());
    }

    @Override
    protected void onResume() {
        super.onResume();
        simpleVideoPlayer.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleVideoPlayer.onPause();
    }
}


// git pull（更新，与github代码同步）
