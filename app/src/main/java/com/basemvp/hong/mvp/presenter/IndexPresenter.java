package com.basemvp.hong.mvp.presenter;

import com.basemvp.hong.mvp.contract.BaseContract;
import com.basemvp.hong.request.BaseObserver;
import com.basemvp.hong.request.RxTransformer;

/**
 * Create by Hong on 2020/4/14 11:10.
 */
public class IndexPresenter<T> extends BasePresenter<BaseContract.view, BaseContract.model> {
    public IndexPresenter(BaseContract.view view) {
        super(view);
    }

    public IndexPresenter(BaseContract.model model, BaseContract.view view) {
        super(model, view);
    }

    public void doGetData() {
        mModel.getData().compose(RxTransformer.transform(getView()))
                .subscribe(new BaseObserver<T>() {
                    @Override
                    public void onSuccess(T response) {
                        getView().onFillData(response);
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().showToast(msg);
                    }
                });
    }

    public void doGetDataLoading() {
        mModel.getData().compose(RxTransformer.transformWithLoadingDialog(getView()))
                .subscribe(new BaseObserver<T>() {
                    @Override
                    public void onSuccess(T response) {

                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });
    }
}
