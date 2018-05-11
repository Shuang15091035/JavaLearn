package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.FundStrategyDao;
import com.jpym.ymfrontgm.model.FundStrategyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundStrategyService {

    @Autowired
    private FundStrategyDao fundStrategyDao;

    public FundStrategyModel searchFundStrategy(String funcCode) throws Exception {
        return fundStrategyDao.searchFundStrategy(funcCode);
    }
}
