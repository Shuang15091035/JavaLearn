package com.jpym.ymfrontgm.requestparammodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBankCardRequestModel {
    private String custno;
    private String certificatetype;
    private String certificateno;
    private String depositacctname;
    private String channelid;
    private String depositacct;
    private String mobileno;
    private String tpasswd;
    private String verificationcode;
}
