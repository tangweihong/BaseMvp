package com.basemvp.hong.mvp.view;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Create by Hong on 2020/4/13 16:33.
 */

public interface IBaseView {
    void loading();

    void getData();

    /**
     * loading finish
     */
    void loadingFinish();

    /**
     * @param text
     */
    void loadingFailed(int res, String text);

    /**
     * show Toast
     *
     * @param message message
     */
    void showToast(String message);

    void showLoading();

    /**
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLifecycle();
}
