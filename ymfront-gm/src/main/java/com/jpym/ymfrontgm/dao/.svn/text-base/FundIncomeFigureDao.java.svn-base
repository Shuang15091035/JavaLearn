package com.jpym.ymfrontgm.dao;

import com.jpym.ymfrontgm.model.FundIncomeFigureModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundIncomeFigureDao {
    List<FundIncomeFigureModel> searchFundIncomeFigure(@Param( "fund_code" ) String fundcode, @Param( "start_date" ) String startdate, @Param( "end_date" ) String enddate) throws Exception;
}
