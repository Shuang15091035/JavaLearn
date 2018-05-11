package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundDetailModel {

    //  基金名称
    @Column( "fundname" )
    private String fundname;
    //    基金代码
    @Column( "fundcode" )
    private String fundcode;
    //基金类型
    @Column( "fundtype" )
    private String fundtype;
    @Column( "subscriptionstatus" )
    private String subscriptionstatus; //是否支持申购状态
    @Column( "fixedinveststate" )
    private String fixedinveststate; //是否支持定投状态
    //TA编号
    @Column( "tano" )
    private String tano;
    //    基金简介
    @Column( "funddescribe" )
    private String funddescribe;
    //    基金标签
    @Column( "labeltype" )
    private String labeltype;
    //    净值日期
    @Column( "navdate" )
    private String navdate;
    //    当日基金净值(单位净值)
    @Column( "nav" )
//    @Column( "unitnav" )
    private String nav;
    //    风险等级
    @Column( "risklevel" )
    private String risklevel;
    @Column( "dividend" )
    private String dividend;
    @Column( "fundincomeunit" )
    private String fundincomeunit;
    @Column( "fundtype2" )
    private String fundtype2; //0:公募,1:私募分级
    @Column( "growthrate" )
    private String growthrate;
    @Column( "step_unit_22" )
    private String step_unit_22;
    @Column( "allud" )
    private String allud;
    @Column( "bidstime" )
    private String bidstime;
    //申购追加最高额
    @Column( "con_per_max_22" )
    private String con_per_max_22;
    //申购追加最低额
    @Column( "con_per_min_22" )
    private String con_per_min_22;
    //申购首次最低额
    @Column( "first_per_min_22" )
    private String first_per_min_22;
    //申购首次最高额
    @Column( "first_per_max_22" )
    private String first_per_max_22;
    //赎回最高
    @Column( "per_max_24" )
    private String per_max_24;
    //转换最高
    @Column( "per_max_36" )
    private String per_max_36;
    //定投最高
    @Column( "per_max_39" )
    private String per_max_39;
    //赎回最低
    @Column( "per_min_24" )
    private String per_min_24;
    //转换最低
    @Column( "per_min_36" )
    private String per_min_36;
    //定投最低
    @Column( "per_min_39" )
    private String per_min_39;
    @Column( "preincomerate" )
    private String preincomerate;
    @Column( "shareclasses" )
    private String shareclasses;
    @Column( "totalnav" )
    private String totalnav;
    @Column( "holdmin" )
    private String holdmin;
    @Column( "holdmax" )
    private String holdmax;
    @Column( "dayincrease" )
    private String dayincrease; //日涨幅
    @Column( "yieldt1y" )
    private String yieldt1y; //最近1年的年化收益率
    @Column( "yieldl2y" )
    private String yieldl2y;
    @Column( "yieldl3y" )
    private String yieldl3y;
    @Column( "yieldl5y" )
    private String yieldl5y;
    @Column( "yieldfound" )
    private String yieldfound;
    @Column( "annualyieldl7d" )
    private String annualyieldl7d; //最近7日年化收益率
    @Column( "annualyieldl1m" )
    private String annualyieldl1m; //近一月收益率
    @Column( "annualyieldl3m" )
    private String annualyieldl3m;
    @Column( "annualyieldl6m" )
    private String annualyieldl6m;
    @Column( "nav3m" )
    private String nav3m;
}
