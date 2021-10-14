package com.basemvp.hong.mvp.presenter;

import android.os.Bundle;

import com.basemvp.hong.mvp.contract.BaseContract;
import com.basemvp.hong.mvp.contract.DetailsContract;
import com.basemvp.hong.mvp.model.BaseModel;
import com.basemvp.hong.mvp.model.entity.VersionEntity;
import com.basemvp.hong.mvp.presenter.impl.IRequestDataPresenter;
import com.basemvp.hong.request.BaseObserver;
import com.basemvp.hong.request.RxTransformer;

/**
 * Create by Hong on 2020/4/14 11:10.
 */
public class DetailsPresenter<T> extends RequestPresenter<T> {
    public DetailsPresenter(DetailsContract.view view) {
        super(view);
    }

    public DetailsPresenter(DetailsContract.model model, DetailsContract.view view) {
        super(model, view);
    }

    public void doGetData1(Bundle bundle) {
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

    @Override
    public DetailsContract.model getModel() {
        return (DetailsContract.model) super.getModel();
    }

    @Override
    protected DetailsContract.view getView() {
        return (DetailsContract.view) super.getView();
    }

    public void doGetData2(Bundle bundle) {
        getModel().getVersion(bundle)
                .compose(RxTransformer.transformWithLoadingDialog(getView()))
                .subscribe(new BaseObserver<VersionEntity>() {
                    @Override
                    public void onSuccess(VersionEntity response) {
                        getView().VersionData(response);
                    }

                    @Override
                    public void onFailure(String error, int code) {
                        getView().LoadingError(error,code);
                    }

                });
    }
}
