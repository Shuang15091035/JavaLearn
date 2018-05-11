package com.jpym.ymfrontgm.datadictionary;

public enum SaveplanDic {

    SaveplanDic_REGULARCASTING("普通定投", "1"),
    SaveplanDic_INTELLIGENTCASTING("智能定投", "2"),
    SaveplanDic_COMBINATIONSET("组合定投", "3");

    private String name;
    private String index;

    private SaveplanDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (SaveplanDic bc : SaveplanDic.values()) {
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
