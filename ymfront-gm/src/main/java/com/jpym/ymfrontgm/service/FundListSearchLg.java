package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.util.JzInterfaceUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class FundListSearchLg extends FundListLg {

    protected FundListSearchLg() throws Exception {
        super();
    }

    public FundListSearchLg(String fundname, String listType, String fundType) throws Exception {
        super(fundname, listType, fundType);
    }

    @Override
    public List fundlistByFundListDic() throws Exception {
        List<Map<String, Object>> fundProducts = new ArrayList<>();
        JSONArray funds = JzInterfaceUtil.jzQueryFundList("", "", "").getJSONArray(0);
        Integer fundSize = funds.size();
        for (int i = 0; i < fundSize; i++) {
            JSONObject fundObj = funds.getJSONObject(i);
            String fundCode = fundObj.getString("fundcode");
            String fundName = fundObj.getString("fundname");
            String fundIdentifier = fundCode + fundName; //基金标识 = 基金名称 + 基金代码
            Pattern pat = Pattern.compile(this.fundname);
            Matcher mat = pat.matcher(fundIdentifier);
            if (mat.find()) {
                fundProducts.add(fundObj);
            }
        }
        return fundProducts;
    }
}
