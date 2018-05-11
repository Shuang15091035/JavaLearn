package com.jpym.ymfrontgm.model;

import com.jpym.ymfrontgm.util.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCustNoModel {
    @Column( "custno" )
    private String custno;
}
