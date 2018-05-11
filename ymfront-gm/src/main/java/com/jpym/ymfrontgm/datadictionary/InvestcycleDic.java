package com.jpym.ymfrontgm.datadictionary;

public enum InvestcycleDic {

    InvestcycleDic_MONTY("每月", "0"),
    InvestcycleDic_WEEK("每周", "1"),
    InvestcycleDic_DOUBLEWEEK("每双周", "2");

    private String name;
    private String index;

    private InvestcycleDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (InvestcycleDic bc : InvestcycleDic.values()) {
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
