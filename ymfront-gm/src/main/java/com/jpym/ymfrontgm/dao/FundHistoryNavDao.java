package com.jpym.ymfrontgm.dao;

import com.jpym.ymfrontgm.model.FundHistoryNavModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundHistoryNavDao {
    List<FundHistoryNavModel> searchFundHistoryNavByPaging(@Param( "fund_code" ) String fundcode, @Param( "start_number" ) int fromIndex, @Param( "end_number" ) int toIndex) throws Exception;
}
