package com.basemvp.hong.request;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Result<T> implements Serializable {
    private  int code;
    private String msg;
    private  T data;

}
