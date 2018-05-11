package com.jpym.ymfrontgm.util;


import com.jpym.ymfrontgm.datadictionary.*;
import com.jpym.ymfrontgm.service.FundListFixedInvestLg;
import com.jpym.ymfrontgm.service.FundListOptSelLg;
import com.jpym.ymfrontgm.service.FundListRankingLg;
import com.jpym.ymfrontgm.service.FundListSearchLg;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class MsgConstant {

    // 统一错误码和成功码
    public static final String ERROR_CODE = "9999";
    public static final String SUCCESS_CODE = "0000";
    // 入参
    public static final String ERROR_MSG_PARAMATER_NULL = "参数不能为空";

    public static final String SUCCESS_MEG_PRODUCT_DETAIL_QUERY = "产品详情查询成功";

    public static final String PAGE_SIZE = "10";

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    //需要校验的参数字典
    public static final Map<String, Class> paramDictionary = new HashMap() {{
        put("invtp", InvtpDic.class);
        put("certificatetype", CertificatetDic.class);
        put("sex", SexDic.class);
        put("country", CountryDic.class);
        put("vocationcode", VocationDic.class);
        put("channelid", ChannelidDic.class);
        put("authenticateflag", AuthenticationMarksDic.class);
        put("isspeclpapertype", QuestionDic.class);
        put("buyflag", BuyFlagDic.class);
        put("vastredeemflag", VastredeemDic.class);
        put("saveplanflag", SaveplanDic.class);
        put("investcycle", InvestcycleDic.class);
        put("riskwarnflag", RiskWarnFlagDic.class);
        put("riskmatching", RiskMatchDic.class);
        put("listtype", FundListDic.class);
        put("fundtype", FundTypeDic.class);
    }};

    public static final Map<FundListDic, Class> fundListDicMap = new HashMap() {{
        put(FundListDic.FundListDic_HOMEPAGEOPTIMIZATION, FundListOptSelLg.class);
        put(FundListDic.FundListDic_RANKINGLIST, FundListRankingLg.class);
        put(FundListDic.FundListDic_FIXINVESTMENTOPTIMIZATION, FundListFixedInvestLg.class);
        put(FundListDic.FundListDic_FUNDSEARCHBYIDENTIFY, FundListSearchLg.class);
    }};

    public static final Map<String, String> fundListConstMapper = new HashMap() {{
        put("fundListSearchLg", "00");
        put("fundListOptSelLg", "01");
        put("fundListRankingLg", "02");
        put("fundListFixedInvestLg", "03");
    }};
}
