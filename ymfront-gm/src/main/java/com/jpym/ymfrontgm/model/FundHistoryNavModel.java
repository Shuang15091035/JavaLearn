package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundHistoryNavModel {

    @Column( "fund_code" )
    private String fundcode;
    @Column( "fund_name" )
    private String fundname;
    @Column( "day_increase" )
    private float dayincrease;
    @Column( "nav_date" )
    private String navdate;
    @Column( "unit_nav" )
    private float unitnav;
    @Column( "acc_unit_nav" )
    private float accunitnav;
    @Column( "nums" )
    private String nums;
}