package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.FundHistoryNavDao;
import com.jpym.ymfrontgm.model.FundHistoryNavModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundHistoryNavService {

    @Autowired
    private FundHistoryNavDao fundHistoryNavDao;

    public List<FundHistoryNavModel> searchFundHistoryNavByPaging(String fundCode, int fromIndex, int toIndex) throws Exception {
        return fundHistoryNavDao.searchFundHistoryNavByPaging(fundCode, fromIndex, toIndex);
    }

}
