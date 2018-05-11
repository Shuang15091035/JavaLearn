package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FundManagerModel {
    //    基金代码
    @Column( "fund_code" )
    private String fundcode;
    //    基金名称
    @Column( "fund_name" )
    private String fundname;
    //    基金经理名称
    @Column( "manager_name" )
    private String managername;
    //    简历
    @Column( "pvc" )
    private String pvc;
    //    出生日期
    @Column( "birth_date" )
    private String birthdate;
    //    性别
    @Column( "sex" )
    private String sex;
    //    学历
    @Column( "grale" )
    private String grale;
}
