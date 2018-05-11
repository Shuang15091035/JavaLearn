package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.FundDocumentDao;
import com.jpym.ymfrontgm.model.FundAssetComponentModel;
import com.jpym.ymfrontgm.model.FundIntroductionModel;
import com.jpym.ymfrontgm.model.TopTenStocksModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundDocumentService {
    @Autowired
    private FundDocumentDao fundDocumentDao;

    public FundIntroductionModel searchFundIntroduction(String fundCode) throws Exception {
        return fundDocumentDao.searchFundIntroduction(fundCode);
    }

    public FundAssetComponentModel searchFundAssetComponent(String fundCode) throws Exception {
        return fundDocumentDao.searchFundAssetComponent(fundCode);
    }

    public List<TopTenStocksModel> searchTopTenStocks(String fundCode) throws Exception {
        return fundDocumentDao.searchTopTenStocks(fundCode);
    }
}
