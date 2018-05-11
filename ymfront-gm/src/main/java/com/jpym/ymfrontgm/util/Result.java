package com.jpym.ymfrontgm.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Result<T> {
    private String code;
    private String msg;
    private T data;
}
