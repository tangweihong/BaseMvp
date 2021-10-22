package com.basemvp.hong.request;


import com.basemvp.hong.mvp.model.entity.HomeEntity;
import com.basemvp.hong.mvp.model.entity.MarketItemEntity;
import com.basemvp.hong.mvp.model.entity.NewsListEntity;
import com.basemvp.hong.mvp.model.entity.Result;
import com.basemvp.hong.mvp.model.entity.StrategyListEntity;
import com.basemvp.hong.mvp.model.entity.VersionEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 版本升级
     */
    @GET("api/version")
    Observable<Result<VersionEntity>> getVersion(
            @Query("platform") String platform
    );

    /**
     * 行情
     *
     * @return
     */
    @GET("api/market")
    Observable<Result<List<MarketItemEntity>>> getMarketList();

    /**
     * 首页
     *
     * @return
     */
    @GET("api/home")
    Observable<Result<HomeEntity>> getHomeAssets();

    /* 策略库
     * <p>
     * * @param page  页码
     * * @param limit 条数
     *
     * @return
     */
    @GET("api/strategyList")
    Observable<Result<StrategyListEntity>> getStrategyList(
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("api/information")
    Observable<Result<NewsListEntity>> getInformationList(
            @Query("page") int page,
            @Query("limit") int limit
    );
}