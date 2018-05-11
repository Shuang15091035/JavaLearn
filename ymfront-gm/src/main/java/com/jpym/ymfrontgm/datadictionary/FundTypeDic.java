package com.jpym.ymfrontgm.datadictionary;

public enum FundTypeDic {
    FundTypeDic_EQUITY("股票型", "0"),
    FundTypeDic_BONDTYPE("债券型", "1"),
    FundTypeDic_CURRENCYRANKINGS("货币排行", "2"),
    FundTypeDic_HYBRID("混合型", "3"),
    SPECIALACCOUNT("专户基金", "4"),
    FundTypeDic_EXPONENTIAL("指数型", "5"),
    FundTypeDic_QDII("QDII型", "6");

    private String name;
    private String index;

    private FundTypeDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (FundTypeDic bc : FundTypeDic.values()) {
            if (bc.index.equals(index)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }
}
