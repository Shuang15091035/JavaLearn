package com.jpym.ymfrontgm.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankInfoModel {
    private String bankcode;
    private String bankname;
    private String bankcity;
    private String channelid;
    private Number upperlimit; //单笔限额
    private String dayupperlimit; //日总限额
    private String channelname;
}
