package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribleApplyModel {
    @Column( "appsheetserialno" )
    private String appsheetserialno;
    @Column( "transactiondate" )
    private String transactiondate;
    @Column( "operdate" )
    private String operdate;
    @Column( "opertime" )
    private String opertime;
    @Column( "confirmeddate" )
    private String confirmeddate;
}
