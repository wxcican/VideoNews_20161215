package com.fuicuiedu.idedemo.videonews_20161215.ui.local;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;

import com.fuicuiedu.idedemo.videonews_20161215.R;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class LocalVideoAdapter extends CursorAdapter{


    public LocalVideoAdapter(Context context) {
        super(context,null,true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return new LocalVideoItem(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        LocalVideoItem item = (LocalVideoItem) view;
        item.bind(cursor);
    }
}
