package com.basemvp.hong.mvp.model;

import android.os.Bundle;

import com.basemvp.hong.common.IntentExtra;
import com.basemvp.hong.mvp.contract.BaseContract;
import com.basemvp.hong.mvp.model.entity.NewsListEntity;
import com.basemvp.hong.mvp.model.entity.Result;

import io.reactivex.Observable;

/**
 * Create by Hong on 2020/4/14 11:31.
 */
public class HomeModel implements BaseContract.model<NewsListEntity> {

    @Override
    public Observable<Result<NewsListEntity>> getData(Bundle bundle) {
        return mApiService.getInformationList(
                bundle.getInt(IntentExtra.PAGE_INDEX, 1),
                bundle.getInt(IntentExtra.PAGE_SIZE, 15));
    }
}
