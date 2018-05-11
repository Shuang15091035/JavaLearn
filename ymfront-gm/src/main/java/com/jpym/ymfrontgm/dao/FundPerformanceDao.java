package com.jpym.ymfrontgm.dao;

import com.jpym.ymfrontgm.model.FundPerformanceModel;
import org.springframework.stereotype.Repository;

@Repository
public interface FundPerformanceDao {
    FundPerformanceModel searchFundPerformance(String fundCode) throws Exception;
}
