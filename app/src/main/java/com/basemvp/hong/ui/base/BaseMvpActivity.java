package com.basemvp.hong.ui.base;

import com.basemvp.hong.mvp.presenter.BasePresenter;

/**
 * Create by Hong on 2020/4/14 11:02.
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    P mPresenter;

    @Override
    protected void initPresenter() {
        mPresenter = getPresenter();
    }

    public abstract P getPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
