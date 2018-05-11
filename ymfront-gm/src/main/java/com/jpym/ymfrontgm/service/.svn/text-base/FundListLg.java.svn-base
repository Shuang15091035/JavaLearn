package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.util.SpringUtil;
import net.sf.json.JSONArray;
import org.springframework.jdbc.BadSqlGrammarException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class FundListLg {

    protected String fundname;
    protected String listType;
    protected String fundType;
    protected GMFundListConfigureService gmFundListConfigureService;
    protected FundDocumentService fundDocumentService;
    protected List<Map<String, Object>> fundProducts = new ArrayList<>();

    protected FundListLg() throws Exception {
    }

    public FundListLg(String fundname, String listType, String fundType) throws Exception {
        this.fundname = fundname;
        this.listType = listType;
        this.fundType = fundType;
        this.gmFundListConfigureService = SpringUtil.getBean(GMFundListConfigureService.class);
        this.fundDocumentService = SpringUtil.getBean(FundDocumentService.class);
    }

    protected JSONArray getConfigureTableInfo() throws Exception {
        JSONArray configedFund = new JSONArray();
        try {
            Object configureTable = gmFundListConfigureService.queryFundListConfigureFile();
            configedFund = JSONArray.fromObject(configureTable);
        } catch (BadSqlGrammarException sqlException) {
            System.out.println(sqlException.getMessage());
            configedFund = JSONArray.fromObject(Collections.emptyList());
        }
        return configedFund;
    }

    public abstract List fundlistByFundListDic() throws Exception;

}
