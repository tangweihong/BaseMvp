package com.basemvp.hong.ui.base;


import android.os.Bundle;

import com.basemvp.hong.mvp.presenter.BasePresenter;
import com.basemvp.hong.mvp.presenter.RequestPresenter;

/**
 * Created by hong on 2020/4/20 10:33.
 */
public abstract class BaseMvpFragment extends BaseFragment {
    protected RequestPresenter mPresenter;


    public abstract RequestPresenter getPresenter();

    public BaseMvpFragment() {
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
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
