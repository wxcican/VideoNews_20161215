package com.fuicuiedu.idedemo.videonews_20161215.ui.local;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.fuicuiedu.idedemo.videonews_20161215.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;


/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class LocalVideoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final String TAG = "LocalVideoFragment";

    @BindView(R.id.gridView)GridView gridView;
    private Unbinder unbinder;
    private LocalVideoAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化当期页面的Loader（加载器，去loader视频数据）
        getLoaderManager().initLoader(0,null,this);
        //初始化适配器
        adapter = new LocalVideoAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_local_video,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        //设置适配器
        gridView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭加载预览图的线程池
        adapter.release();
    }

    // ######################  loderCallBack  start  #####################
    //创建所需loader对象
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                MediaStore.Video.Media._ID,//视频ID
                MediaStore.Video.Media.DATA,//视频文件的路径
                MediaStore.Video.Media.DISPLAY_NAME//视频名称
        };
        return new CursorLoader(
                getContext(),
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,//视频 uri
                projection,
                null,null,null
        );
    }

    //数据加载完成
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //设置数据
        adapter.swapCursor(data);
    }

    //数据加载重置
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
    // ######################  loderCallBack  end  #####################
}
