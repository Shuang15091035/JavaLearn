package com.jpym.ymfrontgm.datadictionary;

public enum RiskMatchDic {
    RiskMatchDic_RISKMATCH("风险不匹配", "0"),
    RiskMatchDic_RISKMISMATCH("风险匹配", "1");

    private String name;
    private String index;

    private RiskMatchDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (RiskMatchDic bc : RiskMatchDic.values()) {
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
