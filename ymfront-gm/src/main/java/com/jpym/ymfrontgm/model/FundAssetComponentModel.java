package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundAssetComponentModel {

    @Column( "key1" )
    private String key1;
    @Column( "equityinve_rto" )
    private String equityinverto;
    @Column( "key2" )
    private String key2;
    @Column( "curfds_rto" )
    private String curfdsrto;
    @Column( "key3" )
    private String key3;
    @Column( "other_rto" )
    private String otherrto;

}
