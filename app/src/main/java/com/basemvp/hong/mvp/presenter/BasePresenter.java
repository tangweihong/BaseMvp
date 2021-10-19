package com.basemvp.hong.mvp.presenter;

import com.basemvp.hong.mvp.model.BaseModel;
import com.basemvp.hong.mvp.presenter.impl.IPresenter;
import com.basemvp.hong.mvp.view.IBaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Create by Hong on 2019/4/13 16:38.
 */
public class BasePresenter<V extends IBaseView, M extends BaseModel> implements IPresenter<V> {

    /**
     * 由于Presenter 经常性的持有Activity 的强引用，如果在一些请求结束之前Activity 被销毁了，Activity对象将无法被回收，此时就会发生内存泄露。
     * 这里我们使用引用和泛型来对MVP中的内存泄漏问题进行改良。
     */
    protected Reference<V> mView;
    protected M mModel;



    protected V getView() {
        return mView.get();
    }

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     * @param view
     */
    public BasePresenter(M model, V view) {
        this.mModel = model;
//        this.mView = view;
        attachView(view);
    }

    public M getModel() {
        return mModel;
    }
    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     *
     * @param view
     */
    public BasePresenter(V view) {
//        this.mView = view;
        attachView(view);
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

}
