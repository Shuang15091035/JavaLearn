package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundPerformanceModel {
    @Column( "fund_code" )
    private String fundcode;
    @Column( "fund_name" )
    private String fundname;
    @Column( "nav1m" )
    private float nav1m;
    @Column( "nav3m" )
    private float nav3m;
    @Column( "nav6m" )
    private float nav6m;
    @Column( "nav1y" )
    private float nav1y;
    @Column( "yieldt1y" )
    private float yieldt1y; //最近1年的年化收益率
    @Column( "yieldl2y" )
    private float yieldl2y;
    @Column( "yieldl3y" )
    private float yieldl3y;
    @Column( "yieldl5y" )
    private float yieldl5y;
    @Column( "yieldfound" )
    private float yieldfound;
    @Column( "annualyieldl7d" )
    private float annualyieldl7d; //最近7日年化收益率
    @Column( "annualyieldl1m" )
    private float annualyieldl1m; //近一月收益率
    @Column( "annualyieldl3m" )
    private float annualyieldl3m;
    @Column( "annualyieldl6m" )
    private float annualyieldl6m;
    @Column( "similarRankL1m" )
    private String similarrankl1m; //近一月同类型排名
    @Column( "similarRankL3m" )
    private String similarrankl3m;
    @Column( "similarRankL6m" )
    private String similarrankl6m;
    @Column( "similarRankL1y" )
    private String similarRankL1y;
}
