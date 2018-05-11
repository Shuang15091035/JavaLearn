package com.jpym.ymfrontgm.datadictionary;

public enum RiskWarnFlagDic {
    RiskWarnFlagDic_NOTREAD("无需阅知", "0"),
    RiskWarnFlagDic_PASSED("已阅知", "1");

    private String name;
    private String index;

    private RiskWarnFlagDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (RiskWarnFlagDic bc : RiskWarnFlagDic.values()) {
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
