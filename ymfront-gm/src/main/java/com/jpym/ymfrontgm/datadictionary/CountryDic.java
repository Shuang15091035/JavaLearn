package com.jpym.ymfrontgm.datadictionary;

public enum CountryDic {

    CountryDic_CHINA("中国", "156");

    private String name;
    private String index;

    private CountryDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (CountryDic bc : CountryDic.values()) {
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
