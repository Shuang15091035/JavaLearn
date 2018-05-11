package com.jpym.ymfrontgm.requestparammodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BgMsgCheckRequestModel {
    private String certificatetype;
    private String certificateno;
    private String depositacctname;
    private String channelid;
    private String depositacct;
    private String mobileno;
    private String verificationcode;
}
