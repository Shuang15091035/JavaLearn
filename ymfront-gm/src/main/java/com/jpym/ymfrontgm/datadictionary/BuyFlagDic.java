package com.jpym.ymfrontgm.datadictionary;

public enum BuyFlagDic {

    BuyFlagDic_NONCOMPULSORYPURCHASE("非强制购买", "0"),
    BuyFlagDic_COMPULSORYPURCHASE("强制购买", "1");

    private String name;
    private String index;
    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }
    private BuyFlagDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (BuyFlagDic bc : BuyFlagDic.values()) {
            if (bc.index.equals(index)) {
                return true;
            }
        }
        return false;
    }
}
