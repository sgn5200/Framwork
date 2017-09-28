package com.cannan.framwork.api;

/**
 * Created by Cannan on 2017/7/27 0027.
 *  为Gson 解析创造类型 父类
 *
 *  泛型为data 解析对象
 *
 */

public class BaseResponse<T> {
    private int code;
    private String message;
    private final int successCode=200;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
