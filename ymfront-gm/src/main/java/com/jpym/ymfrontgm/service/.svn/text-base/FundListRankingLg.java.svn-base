package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.util.JzInterfaceUtil;
import com.jpym.ymfrontgm.util.MsgConstant;
import com.jpym.ymfrontgm.util.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FundListRankingLg extends FundListLg {

    protected FundListRankingLg() throws Exception {
        super();
    }

    public FundListRankingLg(String fundname, String listType, String fundType) throws Exception {
        super(fundname, listType, fundType);
    }

    @Override
    public List fundlistByFundListDic() throws Exception {
        fundType = StringUtil.empty(fundType) ? "" : fundType;
        if (fundType.getBytes("utf-8").length > 6)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[listType]入参数据缓冲区太小");
        switch (fundType) {
            case "":
            case "0":
            case "1":
            case "2":
            case "3":
            case "5":
            case "6": {
                JSONArray funds = JzInterfaceUtil.jzQueryFundList(fundType, "", "").getJSONArray(0);
                fundProducts.addAll(0, funds);
                break;
            }
            default:
                break;
        }
        return fundProducts;
    }
}
