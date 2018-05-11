package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.util.JzInterfaceUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FundListOptSelLg extends FundListLg {

    protected FundListOptSelLg() throws Exception {
        super();
    }

    public FundListOptSelLg(String fundname, String listType, String fundType) throws Exception {
        super(fundname, listType, fundType);
    }

    @Override
    public List fundlistByFundListDic() throws Exception {
        JSONArray configedFund = getConfigureTableInfo();
        JSONArray jzFunds = JzInterfaceUtil.jzQueryFundList("", "", "").getJSONArray(0);
        int jzFundSize = jzFunds.size();
        for (int i = 0; i < configedFund.size(); i++) {
            for (int j = 0; j < jzFundSize; j++) {
                JSONObject fundObj = configedFund.getJSONObject(i);
                JSONObject jzFundObj = jzFunds.getJSONObject(j);
                if ((fundObj.getString("fundcode").trim().equals(jzFundObj.getString("fundcode").trim()) && (fundObj.getString("labeltype").equals("01")))) {
                    jzFundObj.put("labeltype", "公募优选");
                    jzFundObj.put("funddescribe", fundObj.getString("funddescribe"));
                    fundProducts.add(jzFundObj);
                }
            }
        }
        return fundProducts;
    }

}
