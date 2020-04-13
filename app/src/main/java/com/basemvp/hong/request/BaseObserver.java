package com.basemvp.hong.request;

import android.text.TextUtils;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class BaseObserver<T> implements Observer<Result<T>> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Result<T> response) {
        baseResponse(response);
        if (response.code == 10000) {
            if (response.data == null) {
                onSuccess(null);
            } else {
                onSuccess(response.data);

            }
        } else {
            onFailure(response.code + ":" + response.desc);
        }
    }

    public void baseResponse(Result<T> result) {
    }

    @Override
    public void onError(Throwable e) {
//        if (e instanceof ResultException) {
//            onFailure(e.getMessage());
//        } else {
//            String error = ApiException.handleException(e).getMessage();
//            onFailure(error);
//        }
        onFailure(e.getMessage());

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
     * @param msg 服务器返回的数据
     */
    public abstract void onFailure(String msg);


//        public abstract void onError(String errorMsg);


    private void _onSuccess(T responce) {

    }

    private void _onFailure(String msg) {

        if (TextUtils.isEmpty(msg)) {
//                ToastUtils.show(R.string.response_return_error);
        } else {
//                ToastUtils.show(msg);
        }
    }

    private void _onError(String err) {

        Log.e("APIException", err);

    }


}
