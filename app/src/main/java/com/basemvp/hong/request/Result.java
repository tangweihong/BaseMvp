package com.basemvp.hong.request;

import java.io.Serializable;

public class Result<T> implements Serializable {

    /**
     *
     */
    public T data;
    public int code;
    public String desc;
}
