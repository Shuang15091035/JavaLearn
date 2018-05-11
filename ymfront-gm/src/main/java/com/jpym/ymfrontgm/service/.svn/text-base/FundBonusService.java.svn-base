package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.FundBonusDao;
import com.jpym.ymfrontgm.model.FundBonusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundBonusService {
    @Autowired
    private FundBonusDao fundBonusListDao;

    public List<FundBonusModel> searchFundBonus(String fundCode, int fromIndex, int toIndex) throws Exception {

        return fundBonusListDao.searchFundBonus(fundCode, fromIndex, toIndex);
    }
}
