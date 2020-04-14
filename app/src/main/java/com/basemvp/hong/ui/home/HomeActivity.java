package com.basemvp.hong.ui.home;

import com.basemvp.hong.mvp.contract.BaseContract;
import com.basemvp.hong.mvp.model.HomeModel;
import com.basemvp.hong.mvp.model.entity.HomeEntity;
import com.basemvp.hong.mvp.presenter.IndexPresenter;
import com.basemvp.hong.ui.base.BaseMvpActivity;

/**
 * Create by Hong on 2020/4/14 11:30.
 */
public class HomeActivity extends BaseMvpActivity<IndexPresenter> implements BaseContract.view<HomeEntity> {


    @Override
    public void onFillData(HomeEntity data) {

    }

    @Override
    public IndexPresenter getPresenter() {
        return new IndexPresenter(new HomeModel(),this);
    }
}
