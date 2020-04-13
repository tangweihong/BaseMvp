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
    M model;
    Reference reference;
    WeakReference<V> mWeakReference;

    public BasePresenter(M model, V view) {
        this.model = model;
        attachView(view);
    }

    @Override
    public void attachView(V view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public boolean isViewAttach() {
        return false;
    }
}
