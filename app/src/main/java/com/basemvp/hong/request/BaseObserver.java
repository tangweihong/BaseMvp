package com.basemvp.hong.request;

import com.basemvp.hong.mvp.model.entity.Result;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class BaseObserver<T> implements Observer<Result<T>> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Result<T> result) {
        if (result.getErrorCode() == 0) {
            onSuccess(result.getData());
        } else {
            onFailure(result.getErrorMsg(), result.getErrorCode());
        }

    }

    @Override
    public void onError(Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        onFailure(error, -1);
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    public abstract void onSuccess(T response);

    /**
     * 服务器返回数据，但code不在约定成功范围内
     *
     * @param error 服务器返回的数据
     */
    public abstract void onFailure(String error, int code);


}
