package com.basemvp.hong.ui.home;

import android.os.Bundle;
import android.view.View;

import com.basemvp.hong.R;
import com.basemvp.hong.adapter.HomeAdapter;
import com.basemvp.hong.common.IntentExtra;
import com.basemvp.hong.mvp.contract.BaseContract;
import com.basemvp.hong.mvp.model.entity.NewsListEntity;
import com.basemvp.hong.mvp.model.entity.VersionEntity;
import com.basemvp.hong.mvp.presenter.RequestPresenter;
import com.basemvp.hong.request.ApiRetrofit;
import com.basemvp.hong.request.BaseObserver;
import com.basemvp.hong.mvp.model.entity.Result;
import com.basemvp.hong.request.RxTransformer;
import com.basemvp.hong.ui.base.BaseRecyclerListViewActivity;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

/**
 * Create by Hong on 2020/4/14 11:30.
 */
@FConfig(value = R.layout.activity_test, hideToolbar = true)
public class NewsListActivity extends BaseRecyclerListViewActivity<NewsListEntity.DataBean, HomeAdapter>
        implements BaseContract.view<NewsListEntity> {

    @Override
    protected void initView(Bundle save) {
        super.initView(save);
        findViewById(R.id.tv_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(DetailActivity.class);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
            }
        });

    }

    @Override
    public RequestPresenter getPresenter() {
        if (mPresenter == null) {
            mPresenter = new RequestPresenter(new BaseContract.model<NewsListEntity>() {
                @Override
                public Observable<Result<NewsListEntity>> getData(Bundle bundle) {
                    return mApiService.getInformationList(getPageIndex(), getPageSize());
                }
            }, this);
        }
        return mPresenter;
    }

    @Override
    protected Bundle getRequestParams(int pageIndex) {
        Bundle bundle = new Bundle();
        bundle.putInt(IntentExtra.PAGE_INDEX, pageIndex);
        bundle.putInt(IntentExtra.PAGE_SIZE, getPageSize());
        return bundle;
    }

    @Override
    public void onFillData(NewsListEntity data) {
        addListData(data.getData());
    }

}
