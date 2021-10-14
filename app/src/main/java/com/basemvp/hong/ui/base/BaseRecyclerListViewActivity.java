package com.basemvp.hong.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basemvp.hong.R;
import com.basemvp.hong.mvp.presenter.BasePresenter;
import com.basemvp.hong.utils.TypeUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by hong on 2020/5/11 16:54.
 */
public abstract class BaseRecyclerListViewActivity<T, Adapter extends BaseQuickAdapter> extends SwipeRefreshActivity {

    @BindView(R.id.recycler_view)
    public RecyclerView mRecyclerView;

    private Adapter mAdapter;
    protected RecyclerView.LayoutManager layoutManager;

    private int pageIndex = 1;

    private int pageSize = 15;

    protected LinearLayout mEmptyLlt;
    protected ImageView mEmptyImage;
    protected TextView mEmptyText;

    @Override
    protected void initView() {
        super.initView();
        Class adapterClass = (Class) TypeUtil.getSuperclassTypeParameter(getClass(), 1);
        try {
            mAdapter = (Adapter) adapterClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkAdapter();
    }

    private void checkAdapter() {
        if (mAdapter == null) {
            throw new IllegalArgumentException("adapter == null");
        } else {
            onInitView();
        }
    }

    public final void onInitView() {
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(mAdapter);
        setHasFixedSize();
        if (isInitLoadMoreModule()) {
            mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    if (!isRefresh()) {
                        pageIndex++;
                        onGetData();
                    }
                }
            });
            mAdapter.getLoadMoreModule().setAutoLoadMore(true);
            mAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
            mAdapter.getLoadMoreModule().setLoadMoreView(new BaseLoadMoreView() {
                @Override
                public View getRootView(ViewGroup viewGroup) {
                    // 整个 LoadMore 布局
                    return LayoutInflater.from(mContext).inflate(R.layout.view_load_more, viewGroup, false);
                }

                @Override
                public View getLoadingView(BaseViewHolder baseViewHolder) {
                    // 布局中 “加载中”的View
                    return baseViewHolder.findView(R.id.load_more_loading_view);
                }

                @Override
                public View getLoadComplete(BaseViewHolder baseViewHolder) {
                    // 布局中 “当前一页加载完成”的View
                    return baseViewHolder.findView(R.id.load_more_load_complete_view);
                }

                @Override
                public View getLoadEndView(BaseViewHolder baseViewHolder) {
                    // 布局中 “全部加载结束，没有数据”的View
                    return baseViewHolder.findView(R.id.load_more_load_end_view);
                }

                @Override
                public View getLoadFailView(BaseViewHolder baseViewHolder) {
                    // 布局中 “加载失败”的View
                    return baseViewHolder.findView(R.id.load_more_load_fail_view);
                }
            });
        }
    }

    protected void setEmptyView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_list_empty_view, null);
        mEmptyLlt = view.findViewById(R.id.empty_llt);
        mEmptyImage = view.findViewById(R.id.empty_img);
        mEmptyText = view.findViewById(R.id.empty_tv);
        mAdapter.setEmptyView(view);
    }

    @Override
    protected Bundle getRequestParams() {
        return getRequestParams(pageIndex);
    }

    protected abstract Bundle getRequestParams(int pageIndex);

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    /**
     * 是否初始化LoadMoreModule
     *
     * @return 默认true
     */
    public boolean isInitLoadMoreModule() {
        return true;
    }

    /**
     * 设置高度固定
     * <p>
     * 如果item高度是固定的话，可以使用RecyclerView.setHasFixedSize(true);来避免requestLayout浪费资源。
     */
    public void setHasFixedSize() {
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void startRefresh() {
        pageIndex = 1;
        super.startRefresh();
    }

    /**
     * get page index
     *
     * @return page index
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * set the list page no
     *
     * @param pageIndex page index
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * get page pageSize
     *
     * @return page pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * set the list page no
     *
     * @param size page size
     */
    public void setPageSize(int size) {
        this.pageSize = size;
    }

    /**
     * get this recycler view's adapter.
     *
     * @return Adapter
     */
    public Adapter getAdapter() {
        return mAdapter;
    }


    protected void addListData(List<T> mList) {
        if (pageIndex == 1) {
            mAdapter.setList(mList);
        } else {
            mAdapter.addData(mList);
        }
        if (mAdapter.getData().size() < getPageSize()) {
            mAdapter.getLoadMoreModule().loadMoreEnd(true);
        } else {
            mAdapter.getLoadMoreModule().loadMoreComplete();
        }

        if (mAdapter.getData() == null || mAdapter.getData().size() <= 0) {
            setEmptyView();
        }
    }
}
