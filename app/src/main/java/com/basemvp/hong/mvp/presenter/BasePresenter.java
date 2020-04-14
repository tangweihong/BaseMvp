package com.basemvp.hong.mvp.presenter;

import com.basemvp.hong.mvp.model.BaseModel;
import com.basemvp.hong.mvp.presenter.impl.IPresenter;
import com.basemvp.hong.mvp.view.IBaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Create by Hong on 2020/4/13 16:38.
 */
public class BasePresenter<V extends IBaseView, M extends BaseModel> implements IPresenter<V> {

    protected M mModel;
    protected Reference<V> mView;

    public BasePresenter(V view) {
        attachView(view);
    }

    public BasePresenter(M model, V view) {
        this.mModel = model;
        attachView(view);
    }

    public V getView() {
        return mView.get();
    }

    @Override
    public void attachView(V view) {
        this.mView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    @Override
    public boolean isViewAttach() {
        return false;
    }
}
