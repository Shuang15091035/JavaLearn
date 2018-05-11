package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRiskLevelCommitModel {
    @Column( "riskenddate" )
    private String riskenddate;
    @Column( "custrisk" )
    private String custrisk;
    @Column( "point" )
    private String point;
    @Column( "serialno" )
    private String serialno;
    @Column( "busidate" )
    private String busidate;
    @Column( "last_custrisk" )
    private String lastcustrisk;
    @Column( "papertype" )
    private String papertype;
    @Column( "knowledgetestdate" )
    private String knowledgetestdate;
    @Column( "lowriskflag" )
    private String lowriskflag;
}
