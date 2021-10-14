package com.basemvp.hong.ui.base;


import android.os.Bundle;

import com.basemvp.hong.mvp.presenter.RequestPresenter;

/**
 * Created by hong on 2020/4/17 17:34.
 */
public abstract class BaseMvpActivity extends BaseActivity {
    protected RequestPresenter mPresenter;


    public abstract RequestPresenter getPresenter();

    public BaseMvpActivity() {
        mPresenter = getPresenter();
        checkPresenter();
    }

    private void checkPresenter() {
        if (mPresenter == null) {
            throw new IllegalArgumentException("presenter == null");
        }
    }

    public void onGetData() {
        super.onGetData();
        Bundle bundle = getRequestParams();
        mPresenter.doGetData(bundle);
    }

    protected Bundle getRequestParams() {
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
