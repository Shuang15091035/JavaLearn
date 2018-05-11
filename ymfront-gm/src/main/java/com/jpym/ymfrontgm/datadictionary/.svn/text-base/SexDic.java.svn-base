package com.jpym.ymfrontgm.datadictionary;

public enum SexDic {
    SexDic_WOMAN("女", "0"),
    SexDic_MAN("男", "1");

    private String name;
    private String index;

    private SexDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (SexDic bc : SexDic.values()) {
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
