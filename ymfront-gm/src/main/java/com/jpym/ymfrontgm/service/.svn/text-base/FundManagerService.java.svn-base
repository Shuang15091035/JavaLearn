package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.FundManagerDao;
import com.jpym.ymfrontgm.model.FundManagerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundManagerService {
    @Autowired
    private FundManagerDao fundManagerDao;

    public List<FundManagerModel> fundManagerInformation(String fundcode) {
        return fundManagerDao.fundManagerInformation(fundcode);
    }

    ;


}
