package com.jpym.ymfrontgm.datadictionary;

public enum VocationDic {

    VocationDic_GOVERNMENTORGANS("党政机关,事业单位", "01"),
    VocationDic_ENTERPRISEUNIT("企业单位", "02"),
    VocationDic_FREELANCE("自由职业", "03"),
    VocationDic_STUDENT("学生", "04"),
    VocationDic_SOLDIER("军人", "05"),
    VocationDic_OTHER("其他", "06"),
    VocationDic_EDUCATIONAL("教科文", "07"),
    VocationDic_FINANCIAL("金融", "08"),
    VocationDic_TRADE("商贸", "09"),
    VocationDic_REALESTATE("房地产", "10"),
    VocationDic_MANUFACTURING("制造业", "11");

    private String name;
    private String index;

    private VocationDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (VocationDic bc : VocationDic.values()) {
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
