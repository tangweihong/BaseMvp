package com.basemvp.hong.mvp.contract;

import com.basemvp.hong.mvp.model.BaseModel;
import com.basemvp.hong.mvp.view.IBaseView;
import com.basemvp.hong.request.Result;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Create by Hong on 2020/4/13 16:44.
 */

public interface BaseContract {

    interface view<T> extends IBaseView {
        void onFillData(T data);
    }

    interface model<T> extends BaseModel {
        Observable<Result<T>> getData();
    }
}
