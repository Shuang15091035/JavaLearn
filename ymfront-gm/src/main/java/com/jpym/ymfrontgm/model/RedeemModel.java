package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedeemModel {

    @Column( "appsheetserialno" )
    private String appsheetserialno;
    @Column( "transactiondate" )
    private String transactiondate;
    @Column( "operdate" )
    private String operdate;
    @Column( "opertime" )
    private String opertime;
    @Column( "redeemrefunddate" )
    private String redeemrefunddate;
    @Column( "custno" )
    private String custno;
    @Column( "liqustatus" )
    private String liqustatus; //产品流动性管理状态
    @Column( "remainvol" ) //剩余可赎回份额
    private String remainvol;
}
