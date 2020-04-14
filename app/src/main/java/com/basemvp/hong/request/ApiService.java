package com.basemvp.hong.request;



import com.basemvp.hong.mvp.model.entity.HomeEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    /**
     * 密码登录
     *
     * @param mobile   手机号
     * @param password 密码
     */
    @FormUrlEncoded
    @POST("api/auth/signIn")
    Observable<Result<HomeEntity>> login(
            @Field("mobile")
                    String mobile,
            @Field("password")
                    String password);



}