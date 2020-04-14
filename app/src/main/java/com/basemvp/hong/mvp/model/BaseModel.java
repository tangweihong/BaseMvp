package com.basemvp.hong.mvp.model;

import com.basemvp.hong.request.ApiRetrofit;
import com.basemvp.hong.request.ApiService;

/**
 * Create by Hong on 2020/4/13 16:36.
 */
public interface BaseModel {
    ApiService mApiService = ApiRetrofit.getInstance().getApiService();
}
