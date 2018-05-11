package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.BankInfoDao;
import com.jpym.ymfrontgm.model.BankInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankInfoService {
    @Autowired
    private BankInfoDao bankInfoDao;

    public BankInfoModel channelidAndChannelNameByBankName(String channelid) throws Exception {
        return bankInfoDao.channelidAndChannelNameByBankName(channelid);
    }
}
