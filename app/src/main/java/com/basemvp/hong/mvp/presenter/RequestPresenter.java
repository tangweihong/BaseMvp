package com.basemvp.hong.mvp.presenter;

import android.os.Bundle;

import com.basemvp.hong.mvp.contract.BaseContract;
import com.basemvp.hong.mvp.presenter.impl.IRequestDataPresenter;
import com.basemvp.hong.request.BaseObserver;
import com.basemvp.hong.request.RxTransformer;

/**
 * Create by Hong on 2020/4/14 11:10.
 */
public class RequestPresenter<T> extends BasePresenter<BaseContract.view, BaseContract.model>  {
    public RequestPresenter(BaseContract.view view) {
        super(view);
    }

    public RequestPresenter(BaseContract.model model, BaseContract.view view) {
        super(model, view);
    }

    public void doGetData(Bundle bundle) {
        mModel.getData(bundle)
                .compose(RxTransformer.transformWithLoadingDialog(getView()))
                .subscribe(new BaseObserver<T>() {
                    @Override
                    public void onSuccess(T response) {
                        getView().onFillData(response);
                    }

                    @Override
                    public void onFailure(String error, int code) {
                        getView().showToast(error);
                    }

                });
    }


    public void doGetDataDialog(Bundle bundle) {
        mModel.getData(bundle)
                .compose(RxTransformer.transformWithLoadingDialog(getView()))
                .subscribe(new BaseObserver<T>() {
                    @Override
                    public void onSuccess(T response) {

                    }

                    @Override
                    public void onFailure(String error, int code) {

                    }

                });
    }
}
