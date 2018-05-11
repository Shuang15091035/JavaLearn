package com.jpym.ymfrontgm.datadictionary;

public enum AuthenticationMarksDic {
    AuthenticationMarks_NoAuthentication("未鉴权", "0"),
    AuthenticationMarks_Authentication("已鉴权", "1");

    private String name;
    private String index;

    private AuthenticationMarksDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (AuthenticationMarksDic bc : AuthenticationMarksDic.values()) {
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
