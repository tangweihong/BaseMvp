package com.basemvp.hong.ui.base;


import com.basemvp.hong.mvp.presenter.BasePresenter;

/**
 * Created by hong on 2020/4/17 17:34.
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;


    public abstract P getPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
