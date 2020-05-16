package com.feivirus.common.utils.enums;

/**
 * @author feivirus
 */
public enum FromType implements BaseIntegerEnum {
    UNKNOWN(1, "unknown"),
    H5(2, "h5"),
    WEIXIN(3, "weixin"),
    IOS(4, "ios"),
    ANDROID(5, "android"),
    WEB(6, "web");

    private Integer key;

    private String value;

    FromType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}
