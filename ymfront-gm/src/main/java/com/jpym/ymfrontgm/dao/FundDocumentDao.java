package com.jpym.ymfrontgm.dao;

import com.jpym.ymfrontgm.model.FundAssetComponentModel;
import com.jpym.ymfrontgm.model.FundIntroductionModel;
import com.jpym.ymfrontgm.model.TopTenStocksModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundDocumentDao {
    FundIntroductionModel searchFundIntroduction(String fundCode) throws Exception;

    FundAssetComponentModel searchFundAssetComponent(String fundCode) throws Exception;

    List<TopTenStocksModel> searchTopTenStocks(String fundCode) throws Exception;
}
