package com.feivirus.orderpay.enums;

/**
 * @author feivirus
 */
public enum  OrderPayStatusEnum {
    UNPAID(0, "待支付"),
    PAYING(1, "支付中"),
    PAYED(2, "已支付"),
    PAY_FAILED(3, "支付失败");

    private Integer code;
    private String desc;

    OrderPayStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderPayStatusEnum getEnumByCode(Integer code) {
        for(OrderPayStatusEnum statusEnum : OrderPayStatusEnum.values()) {
            if (statusEnum.code.equals(code)) {
                return statusEnum;
            }
        }
        return null;
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
