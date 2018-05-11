package com.jpym.ymfrontgm.requestParam;

import com.jpym.ymfrontgm.util.MsgConstant;

public enum  QueryRequestParam{
    FUNDCODE("基金代码", ""),
    FUNDTYPE("基金类型", ""),
    FUNDNAME("基金名称", ""),
    PAGEINDEX("请求页码", "1"),
    PAGESIZE("每页大小", MsgConstant.PAGE_SIZE);

    private QueryRequestParam(String name, String defaultValue) {
        this.desc = name;
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;
    private String defaultValue;

    public static RequestParamMap buildBydefault( ){
        RequestParamMap queryRequestParam = new RequestParamMap();
        for (QueryRequestParam bc : QueryRequestParam.values()) {
            queryRequestParam.put(bc.name().toLowerCase().toString(),bc.defaultValue);
        }
        return queryRequestParam;
    }
}

