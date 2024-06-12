package com.devsu.clientPerson.util.rest;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Generated
@Getter
@Setter
public class Response<T> {
    private int status;
    private T data;

    public Response(int status, T data) {
        this.status = status;
        this.data = data;
    }
}

