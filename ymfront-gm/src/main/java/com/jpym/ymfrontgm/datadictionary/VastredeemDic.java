package com.jpym.ymfrontgm.datadictionary;

public enum VastredeemDic {

    VastredeemDic_GIVEUPEXCESS("放弃超额", "0"),
    VastredeemDic_CONTINUETOREDEEM("继续赎回", "1");

    private String name;
    private String index;

    private VastredeemDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (VastredeemDic bc : VastredeemDic.values()) {
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
