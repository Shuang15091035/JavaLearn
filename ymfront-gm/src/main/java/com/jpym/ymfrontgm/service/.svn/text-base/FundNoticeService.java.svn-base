package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.FundNoticeDao;
import com.jpym.ymfrontgm.model.FundNoticeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundNoticeService {
    @Autowired
    private FundNoticeDao fundNoticeDao;

    public List<FundNoticeModel> queryFundNotice(String fundcode, int fromIndex, int toIndex) {
        return fundNoticeDao.queryFundNotice(fundcode, fromIndex, toIndex);
    }

}
