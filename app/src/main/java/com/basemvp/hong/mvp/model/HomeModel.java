package com.basemvp.hong.mvp.model;

import com.basemvp.hong.mvp.contract.BaseContract;
import com.basemvp.hong.mvp.model.entity.HomeEntity;
import com.basemvp.hong.request.Result;

import io.reactivex.Observable;

/**
 * Create by Hong on 2020/4/14 11:31.
 */
public class HomeModel implements BaseContract.model<HomeEntity> {

    @Override
    public Observable<Result<HomeEntity>> getData() {
        return mApiService.login("", "");
    }
}
