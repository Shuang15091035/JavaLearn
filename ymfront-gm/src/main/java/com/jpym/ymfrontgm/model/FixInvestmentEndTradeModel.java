package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixInvestmentEndTradeModel {
    @Column( "appsheetserialno" )
    private String appsheetserialno; //申请单编号
    @Column( "createflag" )
    private String createflag; //当天是否已产生定投申请
    @Column( "transactiondate" )
    private String transactiondate; //上报日期
}
