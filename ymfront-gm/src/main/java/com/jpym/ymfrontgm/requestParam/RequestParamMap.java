package com.jpym.ymfrontgm.requestParam;

import com.jpym.ymfrontgm.util.StringUtil;

import java.util.HashMap;

public class RequestParamMap extends HashMap {

    public final <T extends Enum<T>> void set(Enum<T> key, String value){
        this.put(schemakey(key),value);
    }
    public final <T extends Enum<T>> String get(Enum<T> key){
        return StringUtil.empty(schemakey(key))?"":key.name().toLowerCase();
    }
    private final <T extends Enum<T>> String schemakey(Enum<T> key){
        return key.name().toLowerCase();
    }
}
