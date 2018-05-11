package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.FundPerformanceDao;
import com.jpym.ymfrontgm.model.FundPerformanceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundPerformanceService {
    @Autowired
    private FundPerformanceDao fundPerformanceDao;

    public FundPerformanceModel searchFundPerformance(String fundCode) throws Exception {
        return fundPerformanceDao.searchFundPerformance(fundCode);
    }
}
