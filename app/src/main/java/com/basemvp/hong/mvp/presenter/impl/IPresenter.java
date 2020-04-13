package com.basemvp.hong.mvp.presenter.impl;

import com.basemvp.hong.mvp.view.IBaseView;

/**
 * Create by Hong on 2020/4/13 16:39.
 */
public interface IPresenter<V extends IBaseView> {

    /**
     * 绑定view
     * @param view view
     */
    void attachView(V view);

    /**
     * 分离view
     */
    void detachView();

    /**
     * 判断view是否已经销毁
     * @return true 未销毁
     */
    boolean isViewAttach();
}
