package com.basemvp.hong.mvp.model;

import android.os.Bundle;

import com.basemvp.hong.mvp.contract.BaseContract;
import com.basemvp.hong.mvp.contract.DetailsContract;
import com.basemvp.hong.mvp.model.entity.HomeEntity;
import com.basemvp.hong.mvp.model.entity.VersionEntity;
import com.basemvp.hong.request.Result;

import io.reactivex.Observable;

/**
 * Create by Hong on 2020/4/14 11:31.
 */
public class DetaisModel implements DetailsContract.model<HomeEntity> {

    @Override
    public Observable<Result<HomeEntity>> getData(Bundle bundle) {
        return mApiService.getHomeAssets();
    }

    @Override
    public Observable<Result<VersionEntity>> getVersion(Bundle bundle) {
        return mApiService.getVersion(bundle.getString("platform"));
    }
}