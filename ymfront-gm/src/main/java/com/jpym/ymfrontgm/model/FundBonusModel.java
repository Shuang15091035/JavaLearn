package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundBonusModel {

    @Column( "fund_code" )
    private String fundcode;
    @Column( "fund_name" )
    private String fundname;
    @Column( "record_date" )
    private String recorddate; //权益登记日
    @Column( "out_dev_date" )
    private String bonusdate; //场外红利发放日
    @Column( "def_share_mode" )
    private String sharemodel; //分红方式:1现金分红  2红利再投资  3特殊分红
    @Column( "unit_ataxdev" )
    private String unitataxdev; //单位税后红利 (元/份)
    @Column( "nums" )
    private String nums; //单位税后红利 (元/份)

}
