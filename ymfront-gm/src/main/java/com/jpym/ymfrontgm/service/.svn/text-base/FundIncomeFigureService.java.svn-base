package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.FundIncomeFigureDao;
import com.jpym.ymfrontgm.model.FundIncomeFigureModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundIncomeFigureService {
    @Autowired
    private FundIncomeFigureDao fundIncomeFigureDao;

    public List<FundIncomeFigureModel> searchFundIncomeFigure(String fundCode, String startDate, String endDate) throws Exception {
        return fundIncomeFigureDao.searchFundIncomeFigure(fundCode, startDate, endDate);
    }
}
