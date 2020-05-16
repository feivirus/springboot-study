package com.feivirus.common.utils.enums;

/**
 * @author feivirus
 */
public enum ErrorEnum implements BaseIntegerEnum {
    SUCCESS(10000, "成功"),
    FAILURE(99999, "失败");

    private Integer key;

    private String value;

    ErrorEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }
}
