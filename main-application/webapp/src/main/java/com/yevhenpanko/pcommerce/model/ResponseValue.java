package com.yevhenpanko.pcommerce.model;

public class ResponseValue<T> {

    public T value;

    public ResponseValue(T value) {
        this.value = value;
    }
}
