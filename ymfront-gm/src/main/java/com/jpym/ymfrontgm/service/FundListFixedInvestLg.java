package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.model.FundIntroductionModel;
import com.jpym.ymfrontgm.util.JzInterfaceUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FundListFixedInvestLg extends FundListLg {

    protected FundListFixedInvestLg() throws Exception {
        super();
    }

    public FundListFixedInvestLg(String fundname, String listType, String fundType) throws Exception {
        super(fundname, listType, fundType);
    }

    @Override
    public List fundlistByFundListDic() throws Exception {
        JSONArray configedFund = getConfigureTableInfo();
        JSONArray jzFunds = JzInterfaceUtil.jzQueryFundList("", "", "1").getJSONArray(0);
        int jzFundSize = jzFunds.size();
        for (int i = 0; i < configedFund.size(); i++) {
            for (int j = 0; j < jzFundSize; j++) {
                JSONObject fundObj = configedFund.getJSONObject(i);
                JSONObject jzFundObj = jzFunds.getJSONObject(j);
                if ((fundObj.getString("fundcode").trim().equals(jzFundObj.getString("fundcode").trim()) && (fundObj.getString("labeltype").equals("03")))) {
                    FundIntroductionModel fundIntroduction = fundDocumentService.searchFundIntroduction(fundObj.getString("fundcode"));
                    jzFundObj.put("labeltype", "定投优选");
                    jzFundObj.put("funddescribe", fundObj.getString("funddescribe"));
                    jzFundObj.put("yearlimit", fundObj.getString("yearlimit"));
                    jzFundObj.put("fundsize", fundIntroduction == null ? "0.0" : fundIntroduction.getFdtotunit());
                    jzFundObj.put("fundstarlevel", fundObj.getString("fundstarlevel"));
                    // FIXME: 2018/3/28 基金评级字段目前拿到的值为配置表信息
                    fundProducts.add(jzFundObj);
                }
            }
        }
        return fundProducts;
    }
}
