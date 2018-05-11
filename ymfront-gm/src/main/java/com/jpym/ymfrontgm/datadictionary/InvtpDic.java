package com.jpym.ymfrontgm.datadictionary;

public enum InvtpDic {

    InvtpDic_ORGANIZATION("机构", "0"),
    InvtpDic_PERSONAL("个人", "1");

    private String name;
    private String index;

    private InvtpDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (InvtpDic bc : InvtpDic.values()) {
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
