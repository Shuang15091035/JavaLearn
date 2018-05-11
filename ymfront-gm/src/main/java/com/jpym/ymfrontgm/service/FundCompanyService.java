package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.FundCompanyDao;
import com.jpym.ymfrontgm.model.FundCompanyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundCompanyService {

    @Autowired
    private FundCompanyDao fundCompanyDao;

    public FundCompanyModel searchFundCompany(String fundCode) throws Exception {
        return fundCompanyDao.searchFundCompany(fundCode);
    }
}
