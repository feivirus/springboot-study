package com.feivirus.orderpay.enums;

/**
 * @author feivirus
 */
public enum RemoteBankProcessResultEnum {
    NO_PROCESS(0, "待处理"),
    PROCESSED_SUCCESS(1, "处理成功"),
    PROCESSED_FAILED(2, "处理失败"),
    PROCESSING(3, "处理中"),
    PROCESS_TIMEOUT(4, "处理超时")
    ;
    private Integer code;
    private String desc;

    RemoteBankProcessResultEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
