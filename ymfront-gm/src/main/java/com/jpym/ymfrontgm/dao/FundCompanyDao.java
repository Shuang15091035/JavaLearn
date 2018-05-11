package com.jpym.ymfrontgm.dao;

import com.jpym.ymfrontgm.model.FundCompanyModel;
import org.springframework.stereotype.Repository;

@Repository
public interface FundCompanyDao {
    FundCompanyModel searchFundCompany(String fundCode);
}
