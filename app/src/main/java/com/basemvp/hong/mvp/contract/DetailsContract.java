package com.basemvp.hong.mvp.contract;

import android.os.Bundle;

import com.basemvp.hong.mvp.model.entity.VersionEntity;
import com.basemvp.hong.mvp.model.entity.Result;

import io.reactivex.Observable;

/**
 * Create by Hong on 2020/4/13 16:44.
 */

public interface DetailsContract extends BaseContract {

    interface view<T> extends BaseContract.view<T> {

        void VersionData(VersionEntity versionEntity);
    }

    interface model<T> extends BaseContract.model<T> {

        Observable<Result<VersionEntity>> getVersion(Bundle bundle);

    }
}
