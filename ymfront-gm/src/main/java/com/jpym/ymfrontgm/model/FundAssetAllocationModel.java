package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundAssetAllocationModel {
    @Column( "fundcode" )
    private String fundcode;
    @Column( "fundname" )
    private String fundname;
    @Column( "fundtype" )
    private String fundtype;
    @Column( "fundtype2" )
    private String fundtype2;
    @Column( "status" )
    private String status;
    @Column( "fundtotalyesincome" )
    private String fundtotalyesincome; //昨日盈亏
    @Column( "fundtotaladdincome" )
    private String fundtotaladdincome; //全部累计收益
    @Column( "fundtotalvolbalance_mode1" )
    private String fundtotalmarketvalue; // 基金市值
    @Column( "modidate" )
    private String modidate; //持仓更新日期
    @Column( "nav" )
    private String unitnav;
}
