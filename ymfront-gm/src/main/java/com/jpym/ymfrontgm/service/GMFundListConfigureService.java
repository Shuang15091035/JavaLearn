package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.dao.GMFundListConfigureDao;
import com.jpym.ymfrontgm.model.GMFundListConfigureModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GMFundListConfigureService {

    @Autowired
    private GMFundListConfigureDao gmFundListConfigureDao;

    public List<GMFundListConfigureModel> queryFundListConfigureFile() {
        return gmFundListConfigureDao.queryFundListConfigureFile();
    }
}
