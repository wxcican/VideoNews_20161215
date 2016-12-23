package com.fuicuiedu.idedemo.videonews_20161215.ui.base;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.fuicuiedu.idedemo.videonews_20161215.R;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

import static android.media.CamcorderProfile.get;
import static android.os.Build.VERSION_CODES.M;

/**
 * 带下拉刷新及分页加载功能的自定义视图
 * <p>
 * 本API已完成列表视图上下拉获取数据 及 使用适配器适配显示数据的核心业务流程
 * <p>
 * 子类只需重写 queryData(),getLimit()和createItemView(),分别去获取不同数据及创建不同列表项视图即可
 * <p>
 * 列表视图使用 {@link RecyclerView}实现
 * <p>
 * 下拉刷新使用 {@link SwipeRefreshLayout}实现
 * <p>
 * 分页加载使用 {@link Mugen} + {@link ProgressBar} 实现
 * <p>
 * 数据获取使用 {@link NewsApi}实现
 * <p>
 */

public abstract class BaseResourceView<Model,ItemView extends BaseItemView<Model>> extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener, MugenCallbacks{
    public BaseResourceView(Context context) {
        this(context,null);
    }

    public BaseResourceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseResourceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @BindView(R.id.recyclerView)RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)SwipeRefreshLayout refreshLayout;
    @BindView(R.id.progressBar)ProgressBar progressBar;

    //跳过多少条数据
    private int skip = 0;
    //是否已经加载完所有数据（limit VS 服务器返回的数据量）
    private boolean loadAll;
    //数据适配器
    protected ModelAdapter adapter;

    //初始化视图
    private void initView() {
        // TODO: 2016/12/23 0023 NewsApi未初始化
        LayoutInflater.from(getContext()).inflate(R.layout.partial_pager_resource,this,true);
        ButterKnife.bind(this);
        //初始化RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ModelAdapter();
        recyclerView.setAdapter(adapter);
        //配置下拉刷新
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        //配置上拉加载
        Mugen.with(recyclerView,this).start();
    }

    //下拉刷新时来触发的方法
    @Override
    public void onRefresh() {
        // TODO: 2016/12/23 0023 loadall改变
    }

    //--------------------------上拉加载时会触发的方法---------------------
    @Override
    public void onLoadMore() {
        // TODO: 2016/12/23 0023 loadall改变
    }

    @Override
    public boolean isLoading() {
        return progressBar.getVisibility() == View.VISIBLE;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return loadAll;
    }
    //--------------------------上拉加载时会触发的方法---------------------

    // TODO: 2016/12/23 0023  从服务器查询数据
    protected abstract Call queryData(int limit, int skip);

    //每页从服务器获取多少条数据
    protected abstract int getLimit();

    //每个单项数据的视图
    protected abstract ItemView createItemView();

    //RecyclerView的数据适配器
    protected class ModelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private ArrayList<Model> dataSet = new ArrayList<>();

        public void clear(){
            dataSet.clear();
            notifyDataSetChanged();
        }

        public void addData(ArrayList<Model> data){
            dataSet.addAll(data);
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemView itemView = createItemView();
            return new RecyclerView.ViewHolder(itemView) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            //当前项的数据
            Model model = dataSet.get(position);
            //当前项的视图
            ItemView itemView = (ItemView) holder.itemView;
            //将当前项的数据设置到当前项的视图上
            itemView.bindModel(model);
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }
    }
}
