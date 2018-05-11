package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerInfoModel {
    @Column( "mobileno" )
    private String mobileno;
    @Column( "custname" )
    private String custname;
    @Column( "custno" )
    private String custno;
    @Column( "transactionaccountid" )
    private String transactionaccountid;
    @Column( "certificatetype" )
    private String certificatetype;
    @Column( "custrisk" )
    private String custrisk;
    @Column( "riskenddate" )
    private String riskenddate;
    @Column( "certificateno" )
    private String certificateno;
    @Column( "invtp" )
    private String invtp;
    @Column( "status" )
    private String status;
    @Column( "vailddate" )
    private String vailddate;
    @Column( "paymoneyaccount" )
    private String paymoneyaccount;
    @Column( "professionflag" )
    private String professionflag; //投资者类型 0：普通投资者，1：专业投资者
    @Column( "identifyflag" )
    private String identifyflag; //客户是否签署准入协议 0:未准入,1已准入
}
