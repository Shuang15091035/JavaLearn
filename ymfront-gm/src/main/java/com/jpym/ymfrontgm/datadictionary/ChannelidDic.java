package com.jpym.ymfrontgm.datadictionary;

public enum ChannelidDic {

    CertificatetDic_CONSTRUCTIONBANK("建设银行", "F000"),
    CertificatetDic_INDUSTRIALBANK("工业银行", "F001"),
    CertificatetDic_AGRICULTURABANK("农业银行", "F002"),
    CertificatetDic_CHINABANK("中国银行", "F003"),
    CertificatetDic_SOCIETEGENERALE("兴业银行", "F004"),
    CertificatetDic_EVERBRIGHTBANK("光大银行", "F005"),
    CertificatetDic_CHINACITICBANK("中信银行", "F006"),
    CertificatetDic_PUDONGDEVELOPMENTBANK("上海浦东发展银行", "F007"),
    CertificatetDic_BANKOFCOMMUNICATIONS("交通银行", "F008"),
    CertificatetDic_POSTALSAVINGSBANK("中国邮政储蓄银行", "F009"),
    CertificatetDic_PINGANBANK("平安银行", "F010"),
    CertificatetDic_SHANGHAIBANK("上海银行", "F011"),
    CertificatetDic_BEIJINGBANK("北京银行", "F012"),
    CertificatetDic_MERCHANTSBANK("招商银行", "F013"),
    CertificatetDic_GUANGDONGDEVELOPMENTBANK("广发银行", "F014"),
    CertificatetDic_HUAXIABANK("华夏银行", "F016");

    private String name;
    private String index;

    private ChannelidDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(String index) {
        for (ChannelidDic bc : ChannelidDic.values()) {
            if (bc.index.equals(index)) {
                return bc.name;
            }
        }
        return null;
    }

    public static String getIndex(String name) {
        for (ChannelidDic bc : ChannelidDic.values()) {
            if (bc.name.equals(name)) {
                return bc.index;
            }
        }
        return null;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (ChannelidDic bc : ChannelidDic.values()) {
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
