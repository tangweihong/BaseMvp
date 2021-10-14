package com.basemvp.hong.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.basemvp.hong.R;
import com.basemvp.hong.mvp.contract.DetailsContract;
import com.basemvp.hong.mvp.model.DetaisModel;
import com.basemvp.hong.mvp.model.HomeModel;
import com.basemvp.hong.mvp.model.entity.HomeEntity;
import com.basemvp.hong.mvp.model.entity.NewsListEntity;
import com.basemvp.hong.mvp.model.entity.VersionEntity;
import com.basemvp.hong.mvp.presenter.DetailsPresenter;
import com.basemvp.hong.mvp.presenter.RequestPresenter;
import com.basemvp.hong.ui.base.BaseMvpActivity;
import com.basemvp.hong.ui.base.internal.FConfig;
import com.basemvp.hong.utils.XLog;

import butterknife.BindView;

/**
 * Create by Hong on 2020/4/14 13:51.
 */
@FConfig(value = R.layout.activity_main, hideToolbar = true)
public class DetailActivity extends BaseMvpActivity
        implements DetailsContract.view<NewsListEntity> {
    @BindView(R.id.tv_refresh)
    TextView mTvContent;

    @Override
    public DetailsPresenter getPresenter() {
        if (mPresenter == null) {
            mPresenter = new DetailsPresenter(new DetaisModel(), this);
        }
        return (DetailsPresenter) mPresenter;
    }

    @Override
    protected void initView() {
        super.initView();
        findViewById(R.id.tv_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("platform", "Android");
                getPresenter().doGetData2(bundle);
            }
        });
    }

    @Override
    public void onGetData() {
        super.onGetData();
        Bundle bundle = new Bundle();
        bundle.putString("platform", "Android");
        getPresenter().doGetData2(bundle);
    }

    @Override
    public void VersionData(VersionEntity versionEntity) {
        mTvContent.setText(versionEntity.getContent());
    }

    @Override
    public void onFillData(NewsListEntity data) {
        mTvContent.setText(data.getData().get(0).getContent());
    }

}
