package com.basemvp.hong.ui.base;


import android.graphics.Color;
import android.os.Bundle;

import com.basemvp.hong.R;
import com.basemvp.hong.mvp.presenter.BasePresenter;
import com.basemvp.hong.mvp.view.ISwipeRefreshView;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import butterknife.BindView;

public abstract class SwipeRefreshActivity extends BaseMvpActivity implements ISwipeRefreshView {
    @BindView(R.id.refresh_layout)
    protected SmartRefreshLayout vSwipeRefresh;

    @Override
    protected void initView(Bundle save) {
        super.initView(save);
        vSwipeRefresh.setRefreshHeader(new MaterialHeader(mContext));
        vSwipeRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                startRefresh();
            }
        });
    }

    /**
     * 设置仅越界回弹
     */
    protected void setOnlyScrollBounce() {
        vSwipeRefresh.setEnableRefresh(false);//是否启用下拉刷新功能
        vSwipeRefresh.setEnableLoadMore(false);//是否启用上拉加载功能
        vSwipeRefresh.setEnablePureScrollMode(true);//是否启用纯滚动模式
        vSwipeRefresh.setEnableOverScrollBounce(true);//是否启用越界回弹
        vSwipeRefresh.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
    }

    @Override
    public void loadingFinish() {
        super.loadingFinish();
        stopRefresh();
    }

    @Override
    public void stopRefresh() {
        if (vSwipeRefresh != null && vSwipeRefresh.isRefreshing()) {
            vSwipeRefresh.finishRefresh();
        }
    }

    @Override
    public void startRefresh() {
        if (vSwipeRefresh != null && !vSwipeRefresh.isRefreshing()) {
            vSwipeRefresh.autoRefresh();
        }
        onGetData();
    }

    public boolean isRefresh() {
        return vSwipeRefresh.isRefreshing();
    }
}
