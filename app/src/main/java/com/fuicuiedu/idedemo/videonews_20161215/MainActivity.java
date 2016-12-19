package com.fuicuiedu.idedemo.videonews_20161215;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fuicuiedu.idedemo.videoplayer.full.VideoViewActivity;
import com.fuicuiedu.idedemo.videoplayer.part.SimpleVideoPlayer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_part).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
    }

}


// git pull（更新，与github代码同步）
