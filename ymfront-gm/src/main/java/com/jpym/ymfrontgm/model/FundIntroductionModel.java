package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundIntroductionModel {
    @Column( "fund_code" )
    private String fundcode;
    @Column( "fund_name" )
    private String fundname;
    @Column( "fund_date" )
    private String funddate;
    @Column( "fd_tot_unit" )
    private String fdtotunit;
    @Column( "fd_nature" )
    private String fdnature;
    @Column( "manager_name" )
    private String managername;
    @Column( "trustee_name" )
    private String trusteename;
    @Column( "keeper_name" )
    private String keepername;
}
