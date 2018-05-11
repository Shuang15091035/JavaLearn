package com.jpym.ymfrontgm.datadictionary;

public enum CertificatetDic {

    CertificatetDic_IDCARD("身份证", "0");

    private String name;
    private String index;

    private CertificatetDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (CertificatetDic bc : CertificatetDic.values()) {
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
