package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedInvestmentListModel {
    @Column( "custno" )
    private String custno;
    @Column( "fundcode" )
    private String fundcode;
    @Column( "fundname" )
    private String fundname;
    @Column( "firstinvestamount" )
    private String firstinvestamount;
    @Column( "firstinvestdate" )
    private String firstinvestdate;
    @Column( "operdate" )
    private String operdate;
    @Column( "opertime" )
    private String opertime;
    @Column( "nextinvestdate" )
    private String nextinvestdate;
    @Column( "status" )
    private String status;
    @Column( "channelid" )
    private String channelid;
    @Column( "depositacct" )
    private String depositacct;
    @Column( "balance" )
    private String balance;
    @Column( "buyflag" )
    private String buyflag;
    @Column( "holdbalance" )
    private String holdbalance;
    @Column( "nav" )
    private String nav;
    @Column( "riskmatching" )
    private String riskmatching;
    @Column( "investcycle" )
    private String investcycle;
    @Column( "investcyclevalue" )
    private String investcyclevalue;
    @Column( "continueinvestamount" )
    private String continueinvestamount;
    @Column( "totalsucctimes" )
    private String totalsucctimes;
    @Column( "totalsuccamount" )
    private String totalsuccamount;
    @Column( "hasputoff" )
    private String hasputoff;
    @Column( "saveplanflag" )
    private String saveplanflag;
    @Column( "last_buyflag" )
    private String last_buyflag;
    @Column( "sharetype" )
    private String sharetype;
    @Column( "modidate" )
    private String modidate;
    @Column( "taaccountid" )
    private String taaccountid;
    @Column( "transactionaccountid" )
    private String transactionaccountid;
    @Column( "moneyaccount" )
    private String moneyaccount;
    @Column( "buyplanno" )
    private String buyplanno;
    @Column( "tano" )
    private String tano;
}
