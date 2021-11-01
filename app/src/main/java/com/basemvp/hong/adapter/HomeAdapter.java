package com.basemvp.hong.adapter;

import android.view.ViewGroup;

import com.basemvp.hong.R;
import com.basemvp.hong.mvp.model.entity.NewsListEntity;
import com.basemvp.hong.mvp.model.entity.StrategyListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * Create by Hong on 2020/4/14 14:29.
 */
public class HomeAdapter extends BaseQuickAdapter<NewsListEntity.DataBean, BaseViewHolder> implements LoadMoreModule {

    public HomeAdapter() {
        super(R.layout.item_news_list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, NewsListEntity.DataBean listBean) {
        holder.setText(R.id.tv_title, listBean.getTitle());
    }
}
