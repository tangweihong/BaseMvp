package com.basemvp.hong.ui.base;


import com.basemvp.hong.R;
import com.basemvp.hong.mvp.presenter.BasePresenter;
import com.basemvp.hong.mvp.view.ISwipeRefreshView;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import butterknife.BindView;

public abstract class SwipeRefreshActivity extends BaseMvpActivity implements ISwipeRefreshView {
    @BindView(R.id.refresh_layout)
    protected SmartRefreshLayout vSwipeRefresh;

    @Override
    protected void initView() {
        super.initView();
        vSwipeRefresh.setRefreshHeader(new ClassicsHeader(mContext));
        vSwipeRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                startRefresh();
            }
        });
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
