package com.dmersiyanov.ostrovokdreamapp.api;

/**
 * Created by Mitya on 01.10.2017.
 */

public abstract class Response<T> {

    private T data;
    private Error error;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


}
