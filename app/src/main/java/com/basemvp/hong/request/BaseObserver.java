package com.basemvp.hong.request;

import android.text.TextUtils;
import android.util.Log;

import com.basemvp.hong.utils.XLog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class BaseObserver<T> implements Observer<Result<T>> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Result<T> result) {
        XLog.e("eeee", "onNext"+result.toString());
        if (result.getCode() == 200) {
            onSuccess(result.getData());
        } else {
            onFailure(result.getMsg(), result.getCode());
        }

    }

    @Override
    public void onError(Throwable e) {
        XLog.e("eeee", "onError");
        String error = ApiException.handleException(e).getMessage();
        onFailure(error, -1);
    }

    @Override
    public void onComplete() {
        XLog.e("eeee", "onComplete");
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
