package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TopTenStocksModel {

    @Column( "sk_code" )
    private String skcode;
    @Column( "sk_name" )
    private String skname;
    @Column( "accstk_rto" )
    private String accstkrto;
}
