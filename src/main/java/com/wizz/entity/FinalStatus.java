package com.wizz.entity;

public enum FinalStatus {
    WUFENGXIAN(1, "无风险"), YIGAN(2, "易感"), GAODUYIGAN(3, "高度易感"), YISIGANRAN(4, "疑似感染");

    public final int statusId;
    private final String value;
    // 通过这种方式可以为枚举类的变量附加信息
    private FinalStatus(int statusId, String value) {
        this.statusId = statusId;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
