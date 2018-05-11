package com.jpym.ymfrontgm.dao;

import com.jpym.ymfrontgm.model.FundManagerModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundManagerDao {
    List<FundManagerModel> fundManagerInformation(String fundcode);
}
