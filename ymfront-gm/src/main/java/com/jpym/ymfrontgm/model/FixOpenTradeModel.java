package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixOpenTradeModel {
    @Column( "appsheetserialno" )
    private String appsheetserialno;
    @Column( "buyplanno" )
    private String buyplanno;
    @Column( "firstinvestdate" )
    private String firstinvestdate;
    @Column( "operdate" )
    private String operdate;
    @Column( "opertime" )
    private String opertime;
    @Column( "transactiondate" )
    private String transactiondate;
}
