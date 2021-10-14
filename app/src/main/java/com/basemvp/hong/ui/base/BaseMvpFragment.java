package com.basemvp.hong.ui.base;


import com.basemvp.hong.mvp.presenter.BasePresenter;

/**
 * Created by hong on 2020/4/20 10:33.
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mPresenter;


    public abstract P getPresenter();

    public BaseMvpFragment() {
        mPresenter = getPresenter();
        checkPresenter();
    }

    private void checkPresenter() {
        if (mPresenter == null) {
            throw new IllegalArgumentException("presenter == null");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
