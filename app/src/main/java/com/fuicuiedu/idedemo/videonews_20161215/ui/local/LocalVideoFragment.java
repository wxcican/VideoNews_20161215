package com.fuicuiedu.idedemo.videonews_20161215.ui.local;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuicuiedu.idedemo.videonews_20161215.R;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class LocalVideoFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_local_video,container,false);
    }
}
