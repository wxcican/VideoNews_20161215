package com.fuicuiedu.idedemo.videonews_20161215.ui.news.comments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.fuicuiedu.idedemo.videonews_20161215.R;
import com.fuicuiedu.idedemo.videonews_20161215.UserManager;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.entity.NewsEntity;
import com.fuicuiedu.idedemo.videonews_20161215.commons.CommonUtils;
import com.fuicuiedu.idedemo.videonews_20161215.commons.ToastUtils;
import com.fuicuiedu.idedemo.videoplayer.part.SimpleVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class    CommentsActivity extends AppCompatActivity {

        private static final String KEY_NEWS = "KEY_NEWS";

        //对外公开一个跳转进来的方法
    public static void open(Context context, NewsEntity newsEntity){
        Intent intent = new Intent(context,CommentsActivity.class);
        intent.putExtra(KEY_NEWS,newsEntity);
        context.startActivity(intent);
    }

    private NewsEntity newsEntity;

    @BindView(R.id.tvTitle)TextView tvTitle;
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.simpleVideoPlayer)SimpleVideoPlayer simpleVideoPlayer;
    @BindView(R.id.commentsListView)CommentsListView commentsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //拿到新闻数据
        newsEntity = (NewsEntity) getIntent().getSerializableExtra(KEY_NEWS);
        //设置toolbar
        setSupportActionBar(toolbar);
        //给左上角加返回图标(返回按钮)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置标题
        tvTitle.setText(newsEntity.getNewsTitle());
        //初始化simpleVideoPlayer，设置数据源
        String videoPath = CommonUtils.encodeUrl(newsEntity.getVideoUrl());
        simpleVideoPlayer.setVideoPath(videoPath);
        //初始化commentsListView，设置newsid
        commentsListView.setNewsId(newsEntity.getObjectId());
        commentsListView.autoRefresh();
    }

    // #######################   视频相关  start  #####################

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

    // #######################   视频相关  end  #####################

    // #######################   toolbar相关   #####################
    //创建一个菜单栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.activity_comments,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //左上角返回按钮
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        //判断是否离线
        if (UserManager.getInstance().isOffline()){
            ToastUtils.showShort(R.string.please_login_first);
            return true;
        }
        //收藏
        if (item.getItemId() == R.id.menu_item_like){
            // TODO: 2016/12/26 0026 收藏
            ToastUtils.showShort("收藏");
        }
        //评论
        if (item.getItemId() == R.id.menu_item_comment){
            // TODO: 2016/12/26 0026 评论
            ToastUtils.showShort("评论");
        }
        return super.onOptionsItemSelected(item);
    }
}
