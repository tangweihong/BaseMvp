package com.basemvp.hong.mvp.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Result<T> {
    private  int errorCode;
    private String errorMsg;
    private  T data;

}
