package com.jpym.ymfrontgm.datadictionary;

public enum QuestionDic {
    QuestionDic_GMQUESTION("公募问卷", "0"),
    QuestionDic_SMQUESTION("私募问卷", "1");

    private String name;
    private String index;

    private QuestionDic(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static boolean enumValueExistOfCurrent(String index) {
        for (QuestionDic bc : QuestionDic.values()) {
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
