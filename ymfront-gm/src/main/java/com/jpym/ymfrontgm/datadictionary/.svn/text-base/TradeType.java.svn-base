package com.jpym.ymfrontgm.datadictionary;

public enum TradeType {
    TradeType_22("申购", "22"),
    TradeType_24("赎回", "24"),
    TradeType_36("转换", "36"),
    TradeType_39("定投", "39");

    private String name;
    private String index;

    private TradeType(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (TradeType bc : TradeType.values()) {
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
