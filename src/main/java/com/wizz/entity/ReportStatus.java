package com.wizz.entity;

public enum ReportStatus {
    First(1, "有疫区旅居史"), SECOND(2, "接触过流行病高发地区（湖北省）人员"), THIRD(3, "接触过疑似患者"), FOURTH(4, "接触过确诊患者"), FIFTH(5, "无以上情况");

    public final int statusId;
    private final String value;
    // 通过这种方式可以为枚举类的变量附加信息
    private ReportStatus(int statusId, String value) {
        this.statusId = statusId;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
