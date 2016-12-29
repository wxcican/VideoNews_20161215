package com.fuicuiedu.idedemo.videonews_20161215.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuicuiedu.idedemo.videonews_20161215.R;
import com.fuicuiedu.idedemo.videonews_20161215.UserManager;
import com.fuicuiedu.idedemo.videoplayer.list.MediaPlayerManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class NewsFragment extends Fragment {
    @BindView(R.id.newsListView)
    NewsListView newsListView;

    private View view;
    //用于控制最初setUserVisibleHint会返回false
    private boolean isCreated;
    //用于避免当滑动到第三页时，本页再次执行onPause（因为viewpager缓存机制造成），
    private boolean isPause;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_news, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        isCreated = true;
        // 首次进来，自动刷新
        newsListView.post(new Runnable() {
            @Override
            public void run() {
                newsListView.autoRefresh();
            }
        });
    }

    //初始化MediaPlayer
    @Override
    public void onResume() {
        super.onResume();
        //初始化MediaPlayer
        MediaPlayerManager.getsInstance(getContext()).onResume();
        isPause = false;
    }

    //释放MediaPlayer
    @Override
    public void onPause() {
        super.onPause();
        if (isPause) return;
        //释放MediaPlayer
        MediaPlayerManager.getsInstance(getContext()).onPause();
        isPause = true;
    }

    //移除View
    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        移除View
        ((ViewGroup) view.getParent()).removeView(view);
    }

    //清除所有监听（不再需要Ui交互）
    @Override
    public void onDestroy() {
        super.onDestroy();
        //清除所有监听（不再需要Ui交互）
        MediaPlayerManager.getsInstance(getContext()).removeAllListeners();
        isCreated = false;
    }

    //为了避免因为viewpager缓存机制造成的滑动到第二页不会执行前一页fragment的onPause方法
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isCreated) return;
        if (isVisibleToUser) {
            onResume();
        } else {
            onPause();
        }
    }
}
