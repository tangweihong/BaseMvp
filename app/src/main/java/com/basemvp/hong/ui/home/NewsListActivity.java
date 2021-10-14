package com.basemvp.hong.ui.home;

import android.os.Bundle;
import android.view.View;

import com.basemvp.hong.R;
import com.basemvp.hong.adapter.HomeAdapter;
import com.basemvp.hong.common.IntentExtra;
import com.basemvp.hong.mvp.contract.BaseContract;
import com.basemvp.hong.mvp.model.HomeModel;
import com.basemvp.hong.mvp.model.entity.HomeEntity;
import com.basemvp.hong.mvp.model.entity.MarketItemEntity;
import com.basemvp.hong.mvp.model.entity.NewsListEntity;
import com.basemvp.hong.mvp.model.entity.StrategyListEntity;
import com.basemvp.hong.mvp.model.entity.VersionEntity;
import com.basemvp.hong.mvp.presenter.RequestPresenter;
import com.basemvp.hong.request.ApiRetrofit;
import com.basemvp.hong.request.BaseObserver;
import com.basemvp.hong.request.Result;
import com.basemvp.hong.request.RxTransformer;
import com.basemvp.hong.ui.base.BaseMvpActivity;
import com.basemvp.hong.ui.base.BaseRecyclerListViewActivity;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.basemvp.hong.utils.XLog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Create by Hong on 2020/4/14 11:30.
 */
@FConfig(value = R.layout.activity_news, hideToolbar = true)
public class NewsListActivity extends BaseRecyclerListViewActivity<NewsListEntity.DataBean, HomeAdapter>
        implements BaseContract.view<NewsListEntity> {

    @Override
    protected void initView() {
        super.initView();
        findViewById(R.id.tv_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                getData();
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

    private void getData() {
        ApiRetrofit.getInstance().getApiService().getVersion("Android")
                .compose(RxTransformer.transformWithLoadingDialog(this))
                .subscribe(new BaseObserver<VersionEntity>() {
                    @Override
                    public void onSuccess(VersionEntity response) {
                        showToast(response.getContent());
                    }

                    @Override
                    public void onFailure(String error, int code) {
                        showToast(error);
                    }
                });
    }
}
