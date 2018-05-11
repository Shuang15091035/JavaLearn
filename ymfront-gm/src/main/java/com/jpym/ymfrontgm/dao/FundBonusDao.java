package com.jpym.ymfrontgm.dao;

import com.jpym.ymfrontgm.model.FundBonusModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundBonusDao {
    List<FundBonusModel> searchFundBonus(@Param( "fund_code" ) String fundCode, @Param( "start_number" ) int fromIndex, @Param( "end_number" ) int toIndex) throws Exception;
    // FIXME: 2018/3/21 数据入参修改，SQL待完善
}
