package com.aloysius.rest.Util;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Response{
    private String service;
    private String message;
    private Object data;
}
