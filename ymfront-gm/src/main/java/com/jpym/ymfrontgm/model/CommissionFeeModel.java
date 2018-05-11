package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommissionFeeModel {
    @Column( "benchmarkrates" )
    private Number benchmarkrates;
    @Column( "channelid" )
    private String channelid;
    @Column( "discountfee" )
    private Number discountfee;
    @Column( "executionrate" )
    private Number executionrate;
    @Column( "fullname" )
    private String fullname;
}
