package com.basemvp.hong.mvp.view;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Create by Hong on 2020/4/13 16:33.
 */

public interface IBaseView {

    /**
     * 获取数据
     */
    void onGetData();

    /**
     * 加载结束
     */
    void loadingFinish();


    /**
     * 加载失败
     */
    void LoadingError(String msg, int code);

    /**
     * show Toast
     *
     * @param message message
     */
    void showToast(String message);

    /**
     * dialog 加载
     */
    void showLoadingDialog();

    void showLoadingDialog(boolean isCancelable);

    void showLoadingDialog(boolean isCancelable, String msg);

    /**
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLifecycle();

}
