package com.jpym.ymfrontgm.datadictionary;

public enum FundListDic {
    FundListDic_HOMEPAGEOPTIMIZATION("首页优选", "01"),
    FundListDic_RANKINGLIST("排行列表", "02"),
    FundListDic_FIXINVESTMENTOPTIMIZATION("定投优选", "03"),
    FundListDic_FUNDSEARCHBYIDENTIFY("基金搜索，内部标识使用", "");
    private String name;
    private String index;

    private FundListDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (FundListDic bc : FundListDic.values()) {
            if (bc.index.equals(index)) {
                return true;
            }
        }
        return false;
    }

    public static FundListDic queryCurrentDic(String index) {
        for (FundListDic bc : FundListDic.values()) {
            if (bc.index.equals(index)) {
                return bc;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

}
