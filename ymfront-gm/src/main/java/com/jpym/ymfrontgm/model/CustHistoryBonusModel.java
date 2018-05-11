package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustHistoryBonusModel {
    @Column( "fundname" )
    private String fundname;
    @Column( "fundcode" )
    private String fundcode;
    @Column( "defdividendmethod" )
    private String defdividendmethod; //分红方式
    @Column( "dividendperunit" )
    private String dividendperunit; //单位分红金额
    @Column( "confirmedvol" )
    private String confirmedvol; //红利转投资
    @Column( "confirmedamount" )
    private String confirmedamount; //分红总金额
    @Column( "transactioncfmdate" )
    private String transactioncfmdate; //分红日
}
